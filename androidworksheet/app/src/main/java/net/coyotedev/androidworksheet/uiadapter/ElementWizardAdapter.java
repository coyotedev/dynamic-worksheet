package net.coyotedev.androidworksheet.uiadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.coyotedev.androidworksheet.R;

import java.util.ArrayList;
import java.util.List;

import dynamicworksheet.element.ElementWizard;
import dynamicworksheet.element.IElement;

public class ElementWizardAdapter implements IElementAdapter {
    private static final boolean IS_PAGER_SCROLL_SMOOTH = true;

    private List<IElement> addValidations(List<IElement> list, IElement element) {
        list.add(element);
        for (int i = 0; i < element.getChildren().size(); ++i) {
            addValidations(list, (IElement) element.getChildren().get(i));
        }
        return list;
    }

    @Override
    public View build(final IElement element, ViewGroup root, final Context ctx) {
        final View ret = LayoutInflater.from(ctx).inflate(R.layout.v_wizard, null, false);
        final ViewPager pager = ret.findViewById(R.id.id_wizard_pager);
        Button buttonPrev = ret.findViewById(R.id.id_wizard_button_prev);
        Button buttonNext = ret.findViewById(R.id.id_wizard_button_next);
        final ElementWizard wizard = (ElementWizard) element;

        // pager setup
        {
            // страницы здесь - это вьюхи внутри визарда (как контейнеры, так и не объединённые вьюхи)
            final List<View> pages = new ArrayList<>();
            for (IElement it : wizard.getChildren()) {
                pages.add(ElementAdapter.getInstance().build(it, root, ctx));
            }
            pager.setAdapter(new PagerAdapter() {
                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, int position) {
                    View ret = pages.get(position);
                    container.addView(ret);
                    return ret;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }

                @Override
                public int getCount() {
                    return pages.size();
                }

                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                    return view == o;
                }
            });
            pager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        }

        // buttons setup
        {
            buttonPrev.setText(wizard.getPrevCaption());
            buttonPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int curIdx = pager.getCurrentItem();
                    IElement curElement = wizard.getChildren().get(curIdx);
                    List<IElement> validations = new ArrayList<>();
                    addValidations(validations, curElement);
                    boolean valid = true;
                    for (IElement it : validations) {
                        valid &= it.checkValid();
                    }
                    if (valid) {
                        if (curIdx > 0) {
                            pager.setCurrentItem(--curIdx, IS_PAGER_SCROLL_SMOOTH);
                        }
                    }
                }
            });

            buttonNext.setText(wizard.getNextCaption());
            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int curIdx = pager.getCurrentItem();
                    IElement curElement = wizard.getChildren().get(curIdx);
                    List<IElement> validations = new ArrayList<>();
                    addValidations(validations, curElement);
                    boolean valid = true;
                    for (IElement it : validations) {
                        valid &= it.checkValid();
                    }
                    if (valid) {
                        PagerAdapter adapter = pager.getAdapter();
                        if (adapter != null) {
                            if (curIdx < adapter.getCount()) {
                                pager.setCurrentItem(++curIdx, IS_PAGER_SCROLL_SMOOTH);
                            }
                        }
                    }
                }
            });
        }

        // core ui connection setup
        {
            element.setAdapter(new AdapterBase(ret));
        }

        return ret;
    }
}
