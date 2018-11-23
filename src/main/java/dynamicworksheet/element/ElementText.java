package dynamicworksheet.element;

import dynamicworksheet.Value.IValue;
import dynamicworksheet.message.interact.MessageInteractTextChanged;
import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public class ElementText extends ElementBase<String> {

    public ElementText(@Nullable IElement root, IValue<String> value) {
        super(root);
        mType = UIType.Text;
        setValue(value);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapterSubscribes.add(getValue().getObservable().subscribe(new Consumer<String>() {
            @Override
            public void accept(String text) throws Exception {
                getAdapter().onInteract(new MessageInteractTextChanged(text));
            }
        }));
    }
}
