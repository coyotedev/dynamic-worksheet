package core.dynamicworksheet.element;

import core.dynamicworksheet.type.ContainerLayoutType;
import core.dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

/**
 * Элемент - контейнер.
 * Представляет обычный линейный (однонаправленный) layout
 * Возможные типы смотри {@link ContainerLayoutType}
 */
public class ElementContainer extends core.dynamicworksheet.element.ElementBase<Object> {

    private ContainerLayoutType mLayout = ContainerLayoutType.Horizontal;

    public ElementContainer(@Nullable core.dynamicworksheet.element.IElement root) {
        super(root);
        mType = UIType.Container;
    }

    public void setLayout(ContainerLayoutType type) {
        mLayout = type;
    }

    public ContainerLayoutType getLayout() {
        return mLayout;
    }
}
