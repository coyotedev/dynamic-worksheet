package dynamicworksheet.element;

import dynamicworksheet.type.ContainerLayoutType;
import dynamicworksheet.type.UIType;

public class ElementContainer extends ElementBase<Object> {

    protected ContainerLayoutType mLayout = ContainerLayoutType.Horizontal;

    public ElementContainer(IElement root) {
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
