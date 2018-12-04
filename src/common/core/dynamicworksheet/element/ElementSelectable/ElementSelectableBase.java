package core.dynamicworksheet.element.ElementSelectable;

import java.util.List;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.option.Option;
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
            if (tag.equals(it.getValue())) {
                getValue().setValue(tag);
                break;
            }
        }
    }

    @Override
    public void onInteract(core.dynamicworksheet.message.interact.MessageInteract message) {
        super.onInteract(message);
        Class<? extends core.dynamicworksheet.message.interact.MessageInteract> clazz = message.getClass();
        if (clazz.isAssignableFrom(core.dynamicworksheet.message.interact.MessageInteractSelectedChanged.class)) {
            core.dynamicworksheet.message.interact.MessageInteractSelectedChanged msg = (core.dynamicworksheet.message.interact.MessageInteractSelectedChanged) message;
            select(msg.getTag());
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(mLabel.getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String text) throws Exception {
                getAdapter().onInteract(new core.dynamicworksheet.message.interact.MessageInteractTextChanged(text));
            }
        }));
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String text) throws Exception {
                getAdapter().onInteract(new core.dynamicworksheet.message.interact.MessageInteractSelectedChanged(text));
            }
        }));
    }
}
