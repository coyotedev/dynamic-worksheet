package core.dynamicworksheet.element.ElementSelectable;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

/**
 * Элемент - выпадающий список.
 * Лейбл здесь - представление дефолтного айтема списка (общее название)
 */
public class ElementSelect extends core.dynamicworksheet.element.ElementSelectable.ElementSelectableBase {

    public ElementSelect(@Nullable IElement root, IValue<String> value) {
        super(root, value);
        mType = UIType.Select;
    }
}
