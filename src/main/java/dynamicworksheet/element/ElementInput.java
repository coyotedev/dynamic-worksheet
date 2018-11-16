package dynamicworksheet.element;

import dynamicworksheet.Value.IValue;
import dynamicworksheet.type.UIType;

public class ElementInput extends ElementBase<String> {

    private IValue<String> mValue;

    public ElementInput(IElement root, IValue<String> value) {
        super(root);
        mType = UIType.Input;
        mValue = value;
        mObservable.onNext(mValue.getValue());
    }

    @Override
    public void setValue(String value) {
        mValue.setValue(value);
        mObservable.onNext(mValue.getValue());
    }

    @Override
    public String getValue() {
        return mValue.getValue();
    }
}
