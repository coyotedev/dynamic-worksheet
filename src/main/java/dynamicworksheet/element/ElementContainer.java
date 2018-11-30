package dynamicworksheet.element;

import dynamicworksheet.type.ContainerLayoutType;
import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

/**
 * Элемент - контейнер.
 * Представляет обычный линейный (однонаправленный) layout
 * Возможные типы смотри {@link ContainerLayoutType}
 */
public class ElementContainer extends ElementBase<Object> {

    private ContainerLayoutType mLayout = ContainerLayoutType.Horizontal;

    public ElementContainer(@Nullable IElement root) {
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
