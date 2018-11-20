package dynamicworksheet.element;

import dynamicworksheet.type.UIType;
import io.reactivex.annotations.Nullable;

public class ElementWizard extends ElementContainer {

    private String mNextCaption;
    private String mPrevCaption;

    public ElementWizard(@Nullable IElement root) {
        super(root);
        mType = UIType.Wizard;
    }

    public void setNextCaption(String caption) {
        mNextCaption = caption;
    }

    public String getNextCaption() {
        return mNextCaption;
    }

    public void setPrevCaption(String caption) {
        mPrevCaption = caption;
    }

    public String getPrevCaption() {
        return mPrevCaption;
    }
}
