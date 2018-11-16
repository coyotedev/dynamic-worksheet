package dynamicworksheet.element;

import dynamicworksheet.Value.IValue;
import dynamicworksheet.type.UIType;

public class ElementInput extends ElementBase<String> {

    public ElementInput(IElement root, IValue<String> value) {
        super(root);
        mType = UIType.Input;
        mValue = value;
    }
}
