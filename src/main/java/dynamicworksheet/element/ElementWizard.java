package dynamicworksheet.element;

import java.util.List;

import dynamicworksheet.value.ValueSimple;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractPageChangeRequest;
import dynamicworksheet.message.interact.MessageInteractPageChanged;
import dynamicworksheet.type.Direction;
import dynamicworksheet.type.UIType;
import dynamicworksheet.validation.IValidation;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public class ElementWizard extends ElementContainer {

    public class PageBundle {
        private IElement mPage;
        private Direction mDirection;

        public PageBundle(IElement page, Direction direction) {
            mPage = page;
            mDirection = direction;
        }

        public IElement getPage() {
            return mPage;
        }

        public Direction getDirection() {
            return mDirection;
        }
    }

    private String mNextCaption;
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
    public void onInteract(MessageInteract message) {
        super.onInteract(message);
        Class<? extends MessageInteract> clazz = message.getClass();
        if (clazz.isAssignableFrom(MessageInteractPageChangeRequest.class)) {
            List<IElement> children = getChildren();
            if (children.isEmpty()) {
                return;
            }
            MessageInteractPageChangeRequest msg = (MessageInteractPageChangeRequest) message;
            boolean valid = true;
            // Закомментировано в целях дебага
//            List<IElement> validations = new ArrayList<>();
//            addValidations(validations, ((PageBundle) getValue().getValue()).mPage);
//            for (IElement it : validations) {
//                valid &= it.checkValid();
//            }
            IValidation.ValidationHandler validHandler = getValidationHandler();
            if (valid) {
                if (validHandler != null) {
                    validHandler.onPassed();
                }
                switch (msg.getDirection()) {
                    case Prev:
                        if (curIdx > 0) {
                            getValue().setValue(new PageBundle(children.get(--curIdx), Direction.Prev));
                        }
                        break;
                    case Next:
                        if (curIdx < children.size() - 1) {
                            getValue().setValue(new PageBundle(children.get(++curIdx), Direction.Next));
                        }
                        break;
                }
            } else {
                if (validHandler != null) {
                    validHandler.onError("");
                }
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                getAdapter().onInteract(new MessageInteractPageChanged((PageBundle) o));
            }
        }));
    }

    @Override
    public void setChildren(List<IElement> children) {
        super.setChildren(children);
        if (!children.isEmpty()) {
            setValue(new ValueSimple<>(new PageBundle(children.get(0), Direction.Static)));
        }
    }
}
