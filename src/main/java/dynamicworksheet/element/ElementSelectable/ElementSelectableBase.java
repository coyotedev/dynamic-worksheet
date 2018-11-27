package dynamicworksheet.element.ElementSelectable;

import java.util.List;

import dynamicworksheet.value.IValue;
import dynamicworksheet.element.ElementBase;
import dynamicworksheet.element.IElement;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractSelectedChanged;
import dynamicworksheet.message.interact.MessageInteractTextChanged;
import dynamicworksheet.option.Option;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public abstract class ElementSelectableBase extends ElementBase<String> {
    private IValue<String> mLabel;
    private List<Option> mOptions;

    ElementSelectableBase(@Nullable IElement root, IValue<String> value) {
        super(root);
        setValue(value);
    }

    public void setLabel(IValue<String> label) {
        mLabel = label;
    }

    public String getLabel() {
        return mLabel.getValue();
    }

    public void setOptions(List<Option> options) {
        if (options == null && options.isEmpty()) {
            return;
        }
        mOptions = options;
    }

    public List<Option> getOptions() {
        return mOptions;
    }

    private void select(String tag) {
        for (Option it : mOptions) {
            if (tag.equals(it.mValue)) {
                getValue().setValue(tag);
                break;
            }
        }
    }

    @Override
    public void onInteract(MessageInteract message) {
        super.onInteract(message);
        Class<? extends MessageInteract> clazz = message.getClass();
        if (clazz.isAssignableFrom(MessageInteractSelectedChanged.class)) {
            MessageInteractSelectedChanged msg = (MessageInteractSelectedChanged) message;
            select(msg.mTag);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(mLabel.getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String text) throws Exception {
                getAdapter().onInteract(new MessageInteractTextChanged(text));
            }
        }));
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String text) throws Exception {
                getAdapter().onInteract(new MessageInteractSelectedChanged(text));
            }
        }));
    }
}
