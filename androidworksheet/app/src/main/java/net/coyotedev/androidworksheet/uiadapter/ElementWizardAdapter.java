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
import android.widget.ScrollView;

import net.coyotedev.androidworksheet.R;

import java.util.ArrayList;
import java.util.List;

import dynamicworksheet.element.ElementWizard;
import dynamicworksheet.element.IElement;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractPageChangeRequest;
import dynamicworksheet.message.interact.MessageInteractPageChanged;
import dynamicworksheet.type.Direction;
import dynamicworksheet.util.mutablevalue.MutableValue;
import dynamicworksheet.validation.IValidation;

public class ElementWizardAdapter implements IElementAdapter {
    private static final boolean IS_PAGER_SCROLL_SMOOTH = true;

    @Override
    public View build(final IElement element, final ViewGroup root, final Context ctx) {
        final View ret = LayoutInflater.from(ctx).inflate(R.layout.v_wizard, root, false);
        final ViewPager pager = ret.findViewById(R.id.id_wizard_pager);
        final Button buttonPrev = ret.findViewById(R.id.id_wizard_button_prev);
        final Button buttonNext = ret.findViewById(R.id.id_wizard_button_next);
        final ElementWizard wizard = (ElementWizard) element;
        final List<View> pages = new ArrayList<>();
        final MutableValue<Direction> direction = new MutableValue<>(Direction.Static);

        // pager setup
        {
            // отключаем возможность свайпать страницы
            pager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });

            pager.setAdapter(new PagerAdapter() {
                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, int position) {
                    View page = pages.get(position);
                    if (page.getParent() != container) {
                        container.addView(page);
                    }
                    return page;
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

                @Override
                public int getItemPosition(@NonNull Object object) {
                    int index = pages.indexOf(object);
                    if (index == -1) {
                        return POSITION_NONE;
                    } else {
                        return index;
                    }
                }
            });

            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                int scrollPosition = 0;

                @Override
                public void onPageScrolled(int i, float v, int i1) {
                    scrollPosition = i;
                }

                @Override
                public void onPageSelected(int i) {

                }

                @Override
                public void onPageScrollStateChanged(int i) {
                    if (i == ViewPager.SCROLL_STATE_IDLE) {
                        PagerAdapter adapter = pager.getAdapter();
                        if (scrollPosition == 0) {
                            if (adapter != null) {
                                pages.remove(1);
                                adapter.notifyDataSetChanged();
                            }
                            buttonPrev.setEnabled(true);
                        }
                        if (scrollPosition == 1) {
                            if (adapter != null) {
                                pages.remove(0);
                                adapter.notifyDataSetChanged();
                            }
                            buttonNext.setEnabled(true);
                        }
                    }
                }
            });
        }

        // buttons setup
        {
            buttonPrev.setText(wizard.getPrevCaption());
            buttonPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    direction.setValue(Direction.Prev);
                    wizard.onInteract(new MessageInteractPageChangeRequest(Direction.Prev));
                }
            });

            buttonNext.setText(wizard.getNextCaption());
            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    direction.setValue(Direction.Next);
                    wizard.onInteract(new MessageInteractPageChangeRequest(Direction.Next));
                }
            });
        }

        // core ui connection setup
        {
            element.setAdapter(new AdapterBase(ret) {
                @Override
                public void onInteract(MessageInteract message) {
                    super.onInteract(message);
                    if (message.getClass().isAssignableFrom(MessageInteractPageChanged.class)) {
                        MessageInteractPageChanged msg = (MessageInteractPageChanged) message;
                        PagerAdapter adapter = pager.getAdapter();

                        ScrollView scr = new ScrollView(ctx);
                        scr.setFillViewport(true);
                        scr.addView(ElementAdapter.getInstance().build(msg.mPage.mPage, root, ctx));
                        switch (msg.mPage.mDirection) {
                            case Prev: {
                                if (adapter != null) {
                                    pages.add(0, scr);
                                    adapter.notifyDataSetChanged();
                                    pager.setCurrentItem(0, IS_PAGER_SCROLL_SMOOTH);
                                }
                                buttonNext.setEnabled(true);
                                break;
                            }
                            case Next: {
                                if (adapter != null) {
                                    pages.add(scr);
                                    adapter.notifyDataSetChanged();
                                    pager.setCurrentItem(1, IS_PAGER_SCROLL_SMOOTH);
                                }
                                buttonPrev.setEnabled(true);
                                break;
                            }
                            case Static: {
                                // initial (first) page adding
                                if (adapter != null) {
                                    pages.add(scr);
                                    adapter.notifyDataSetChanged();
                                }
                                break;
                            }
                        }
                    }
                }
            });
            element.setValidationHandler(new IValidation.ValidationHandler() {
                @Override
                public void onPassed() {
                    switch (direction.getValue()) {
                        case Prev:
                            buttonPrev.setEnabled(false);
                            break;
                        case Next:
                            buttonNext.setEnabled(false);
                            break;
                    }
                }

                @Override
                public void onError(String error) {

                }
            });
        }

        return ret;
    }
}
