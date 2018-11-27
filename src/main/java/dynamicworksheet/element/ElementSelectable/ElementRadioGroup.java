package dynamicworksheet.element.ElementSelectable;

import dynamicworksheet.value.IValue;
import dynamicworksheet.element.IElement;
import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

public class ElementRadioGroup extends ElementSelectableBase {

    public ElementRadioGroup(@Nullable IElement root, IValue<String> value) {
        super(root, value);
        mType = UIType.RadioGroup;
    }
}
