package core.dynamicworksheet.element;

import java.util.ArrayList;
import java.util.List;

import core.dynamicworksheet.validation.IValidation;
import core.dynamicworksheet.value.ValueSimple;
import core.dynamicworksheet.type.Direction;
import core.dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

/**
 * Элемент визард (набор страниц (шагов), объдинённых в один блок с кнопками "назад" и "далее")
 * Базовое значение - объект типа {@link PageBundle}
 * Передаваемые сигналы:
 * - сигнал об изменении актуальной страницы {@link core.dynamicworksheet.message.interact.MessageInteractPageChanged}
 * Получаемые сигналы:
 * - сигнал об изменении актуальной страницы {@link core.dynamicworksheet.message.interact.MessageInteractPageChanged}
 */
public class ElementWizard extends ElementContainer {
    /**
     * Представление действия по изменению актуальной страницы.
     * Хранит новую страницу и направление изменения {@link Direction} (может быть статичным - это
     * значит, что страница была просто загружена, а не прокручена.
     */
    public static class PageBundle {

        public static class BoundsState {
            private boolean mIsStartBoundReached;
            private boolean mIsEndBoundReached;

            BoundsState(boolean start, boolean end) {
                mIsStartBoundReached = start;
                mIsEndBoundReached = end;
            }

            public boolean isStartBoundReached() {
                return mIsStartBoundReached;
            }

            public boolean isEndBoundReached() {
                return mIsEndBoundReached;
            }
        }

        /** Элемент, представляющий страницу */
        private IElement mPage;
        /** Направление смены страницы */
        private Direction mDirection;
        /** Признаки состояния границ (возможность пойти на страницу до или после текущей) */
        private BoundsState mBoundsState;

        PageBundle(IElement page, Direction direction, BoundsState boundState) {
            mPage = page;
            mDirection = direction;
            mBoundsState = boundState;
        }

        public IElement getPage() {
            return mPage;
        }

        public Direction getDirection() {
            return mDirection;
        }

        public BoundsState getBoundsState() {
            return mBoundsState;
        }
    }

    /** Титульный текст кнопки "Далее" */
    private String mNextCaption;
    /** Титульный текст кнопки "Назад" */
    private String mPrevCaption;
    private int curIdx = 0;

    public ElementWizard(@Nullable IElement root) {
        super(root);
        mType = UIType.Wizard;
    }

    public void setNextCaption(String caption) {
        mNextCaption = caption;
    }

    public String getNextCaption() {
        return mNextCaption;
    }

    public void setPrevCaption(String caption) {
        mPrevCaption = caption;
    }

    public String getPrevCaption() {
        return mPrevCaption;
    }

    private List<IElement> addValidations(List<IElement> list, IElement element) {
        list.add(element);
        for (int i = 0; i < element.getChildren().size(); ++i) {
            addValidations(list, (IElement) element.getChildren().get(i));
        }
        return list;
    }

    @Override
    public void onInteract(core.dynamicworksheet.message.interact.MessageInteract message) {
        super.onInteract(message);
        Class<? extends core.dynamicworksheet.message.interact.MessageInteract> clazz = message.getClass();
        if (clazz.isAssignableFrom(core.dynamicworksheet.message.interact.MessageInteractPageChangeRequest.class)) {
            List<IElement> children = getChildren();
            if (children.isEmpty()) {
                return;
            }
            core.dynamicworksheet.message.interact.MessageInteractPageChangeRequest msg = (core.dynamicworksheet.message.interact.MessageInteractPageChangeRequest) message;
            boolean valid = true;
            // Закомментировано в целях дебага
//            List<IElement> validations = new ArrayList<>();
//            addValidations(validations, ((PageBundle) getValue().getValue()).mPage);
//            for (IElement it : validations) {
//                valid &= it.checkValid();
//            }
            IValidationHandler validHandler = getValidationHandler();
            if (valid) {
                if (validHandler != null) {
                    validHandler.onPassed();
                }
                switch (msg.getDirection()) {
                    case Prev:
                        if (curIdx > 0) {
                            int newIdx = --curIdx;
                            getValue().setValue(new PageBundle(children.get(newIdx), Direction.Prev, new PageBundle.BoundsState(newIdx > 0, true)));
                        }
                        break;
                    case Next:
                        if (curIdx < children.size() - 1) {
                            int newIdx = ++curIdx;
                            getValue().setValue(new PageBundle(children.get(newIdx), Direction.Next, new PageBundle.BoundsState(true, newIdx < children.size() - 1)));
                        }
                        break;
                }
            } else {
                if (validHandler != null) {
                    validHandler.onError(new ArrayList<>());
                }
            }
        }
    }

    @Override
    public void setAdapter(IElement.Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                getAdapter().onInteract(new core.dynamicworksheet.message.interact.MessageInteractPageChanged((PageBundle) o));
            }
        }));
    }

    @Override
    public void setChildren(List<IElement> children) {
        super.setChildren(children);
        if (!children.isEmpty()) {
            setValue(new ValueSimple<>(new PageBundle(children.get(0), Direction.Static, new PageBundle.BoundsState(false, children.size() > 1))));
        }
    }
}
