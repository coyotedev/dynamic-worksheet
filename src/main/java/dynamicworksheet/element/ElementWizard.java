package dynamicworksheet.element;

import dynamicworksheet.type.UIType;

public class ElementWizard extends ElementContainer {

    private String mNext;
    private String mPrev;

    public ElementWizard(IElement root) {
        super(root);
        mType = UIType.Wizard;
    }

    public void setNext(String caption) {
        mNext = caption;
    }

    public String getNext() {
        return mNext;
    }

    public void setPrev(String caption) {
        mPrev = caption;
    }

    public String getPrev() {
        return mPrev;
    }
}
