package core.dynamicworksheet.element.ElementSelectable;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

/**
 * Элемент - RadioGroup.
 * Представляет собой некоторое количество RadioButton'ов, сгруппированных под одним лейблом.
 */
public class ElementRadioGroup extends core.dynamicworksheet.element.ElementSelectable.ElementSelectableBase {

    public ElementRadioGroup(@Nullable IElement root, IValue<String> value) {
        super(root, value);
        mType = UIType.RadioGroup;
    }
}
