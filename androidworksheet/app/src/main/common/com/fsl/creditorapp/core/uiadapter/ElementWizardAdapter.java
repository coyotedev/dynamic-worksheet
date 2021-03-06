package com.fsl.creditorapp.core.uiadapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.fsl.creditorapp.R;

import java.util.ArrayList;
import java.util.List;

import core.dynamicworksheet.element.ElementWizard;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.message.interact.MessageInteract;
import core.dynamicworksheet.message.interact.MessageInteractPageChangeRequest;
import core.dynamicworksheet.message.interact.MessageInteractPageChanged;
import core.dynamicworksheet.type.Direction;
import core.dynamicworksheet.util.mutablevalue.MutableValue;

public class ElementWizardAdapter implements IElementAdapter {
    private static final boolean IS_PAGER_SCROLL_SMOOTH = true;

    class ButtonsManager {
        private AppCompatButton mPrev;
        private AppCompatButton mNext;
        private Context mCtx;

        ButtonsManager(Context ctx, AppCompatButton prev, AppCompatButton next) {
            mPrev = prev;
            mNext = next;
            mCtx = ctx;
        }

        void setEnabled(boolean prev, boolean next) {
            ((Activity) mCtx).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPrev.setEnabled(prev);
                    mNext.setEnabled(next);
                }
            });
        }
    }

    @Override
    public View build(final IElement element, final ViewGroup root, final Context ctx) {
        final View ret = LayoutInflater.from(ctx).inflate(R.layout.v_wizard, root, false);
        final ViewPager pager = ret.findViewById(R.id.id_wizard_pager);
        final AppCompatButton buttonPrev = ret.findViewById(R.id.id_wizard_button_prev);
        final AppCompatButton buttonNext = ret.findViewById(R.id.id_wizard_button_next);
        final ButtonsManager buttonsManager = new ButtonsManager(ctx, buttonPrev, buttonNext);
        final ElementWizard wizard = (ElementWizard) element;
        final List<View> pages = new ArrayList<>();
        final MutableValue<Direction> direction = new MutableValue<>(Direction.Static);
        final MutableValue<ElementWizard.PageBundle.BoundsState> boundState = new MutableValue<>(null);

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
                        }
                        if (scrollPosition == 1) {
                            if (adapter != null) {
                                pages.remove(0);
                                adapter.notifyDataSetChanged();
                            }
                        }
                        ElementWizard.PageBundle.BoundsState state = boundState.getValue();
                        if (state != null) {
                            buttonsManager.setEnabled(state.isStartBoundReached(), state.isEndBoundReached());
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
                    buttonsManager.setEnabled(false, false);
                    direction.setValue(Direction.Prev);
                    wizard.onInteract(new MessageInteractPageChangeRequest(Direction.Prev));
                }
            });

            buttonNext.setText(wizard.getNextCaption());
            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonsManager.setEnabled(false, false);
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
                        boundState.setValue(msg.getPageBundle().getBoundsState());

                        ScrollView scr = new ScrollView(ctx);
                        scr.setFillViewport(true);
                        scr.addView(ElementAdapter.getInstance().build(msg.getPageBundle().getPage(), root, ctx));
                        switch (msg.getPageBundle().getDirection()) {
                            case Prev: {
                                if (adapter != null) {
                                    pages.add(0, scr);
                                    adapter.notifyDataSetChanged();
                                    pager.setCurrentItem(0, IS_PAGER_SCROLL_SMOOTH);
                                }
                                break;
                            }
                            case Next: {
                                if (adapter != null) {
                                    pages.add(scr);
                                    adapter.notifyDataSetChanged();
                                    pager.setCurrentItem(1, IS_PAGER_SCROLL_SMOOTH);
                                }
                                break;
                            }
                            case Static: {
                                // initial (first) page adding
                                if (adapter != null) {
                                    pages.add(scr);
                                    adapter.notifyDataSetChanged();
                                    ElementWizard.PageBundle.BoundsState state = boundState.getValue();
                                    buttonsManager.setEnabled(state.isStartBoundReached(), state.isEndBoundReached());
                                }
                                break;
                            }
                        }
                    }
                }
            });
            element.setValidationHandler(new IElement.IValidationHandler() {
                @Override
                public void onPassed() {}

                @Override
                public void onError(List<String> errors) {
                    ElementWizard.PageBundle.BoundsState state = boundState.getValue();
                    buttonsManager.setEnabled(state.isStartBoundReached(), state.isEndBoundReached());
                }
            });
        }

        return ret;
    }
}
