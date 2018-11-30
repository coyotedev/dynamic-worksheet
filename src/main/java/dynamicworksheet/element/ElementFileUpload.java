package dynamicworksheet.element;

import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractFileChanged;
import dynamicworksheet.type.UIType;
import dynamicworksheet.validation.ValidationUpload;
import dynamicworksheet.value.IValue;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public class ElementFileUpload extends ElementBase<ValidationUpload.FileParams> {
    private String mPlaceholder;

    public ElementFileUpload(@Nullable IElement root, IValue<ValidationUpload.FileParams> value) {
        super(root);
        mType = UIType.FileUpload;
        setValue(value);
    }

    public void setPlaceholder(String placeholder) {
        mPlaceholder = placeholder;
    }

    public String getPlaceholder() {
        return mPlaceholder;
    }

    @Override
    public void onInteract(MessageInteract message) {
        super.onInteract(message);
        if (message.getClass().isAssignableFrom(MessageInteractFileChanged.class)) {
            MessageInteractFileChanged msg = (MessageInteractFileChanged) message;
            getValue().setValue(msg.getParams());
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<ValidationUpload.FileParams>() {
            @Override
            public void accept(ValidationUpload.FileParams fileParams) throws Exception {
                getAdapter().onInteract(new MessageInteractFileChanged(fileParams));
            }
        }));
    }
}
