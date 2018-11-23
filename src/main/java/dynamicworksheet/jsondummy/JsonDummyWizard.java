package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import dynamicworksheet.element.ElementBase;
import dynamicworksheet.element.ElementWizard;
import dynamicworksheet.element.IElement;

public class JsonDummyWizard extends JsonDummyContainer {
    @SerializedName("next")
    public String mNextCaption;
    @SerializedName("prev")
    public String mPrevCaption;

    @Override
    public IElement getElement(IElement root) {
        ElementWizard ret = new ElementWizard(root);
        set(ret);
        return ret;
    }

    @Override
    protected void set(ElementBase element) {
        super.set(element);
        ElementWizard casted = (ElementWizard) element;
        casted.setNextCaption(mNextCaption);
        casted.setPrevCaption(mPrevCaption);
    }
}
