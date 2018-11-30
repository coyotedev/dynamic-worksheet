package dynamicworksheet.element.ElementSelectable;

import dynamicworksheet.value.IValue;
import dynamicworksheet.element.IElement;
import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

/**
 * Элемент - RadioGroup.
 * Представляет собой некоторое количество RadioButton'ов, сгруппированных под одним лейблом.
 */
public class ElementRadioGroup extends ElementSelectableBase {

    public ElementRadioGroup(@Nullable IElement root, IValue<String> value) {
        super(root, value);
        mType = UIType.RadioGroup;
    }
}
