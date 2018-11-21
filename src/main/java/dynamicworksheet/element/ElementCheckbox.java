package dynamicworksheet.element;

import dynamicworksheet.Value.IValue;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractCheckedChanged;
import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public class ElementCheckbox extends ElementBase<Boolean> {

    private String mLabel;

    public ElementCheckbox(@Nullable IElement root, IValue<Boolean> value) {
        super(root);
        mType = UIType.CheckBox;
        setValue(value);
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getLabel() {
        return mLabel;
    }

    @Override
    public void onInteract(MessageInteract message) {
        super.onInteract(message);
        Class<? extends MessageInteract> clazz = message.getClass();
        if (clazz.isAssignableFrom(MessageInteractCheckedChanged.class)) {
            MessageInteractCheckedChanged mess = (MessageInteractCheckedChanged) message;
            getValue().setValue(mess.mIsChecked);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        getValue().getObservable().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean value) throws Exception {
                adapter.onInteract(new MessageInteractCheckedChanged(value));
            }
        });
    }
}
