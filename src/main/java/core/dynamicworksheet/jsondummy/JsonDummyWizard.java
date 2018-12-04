package core.dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.ElementWizard;
import core.dynamicworksheet.element.IElement;

public class JsonDummyWizard extends core.dynamicworksheet.jsondummy.JsonDummyContainer {
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
