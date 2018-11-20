package dynamicworksheet.element;

import dynamicworksheet.Value.IValue;
import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

public class ElementCheckbox extends ElementBase<Boolean> {

    public ElementCheckbox(@Nullable IElement root, IValue<Boolean> value) {
        super(root);
        mType = UIType.CheckBox;
        setValue(value);
    }
}
