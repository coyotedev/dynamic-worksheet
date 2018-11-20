package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
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
        ret.setId(mId);
        ret.setValidations(mValidations);
        // TODO: hidden
        ret.setNextCaption(mNextCaption);
        ret.setPrevCaption(mPrevCaption);
        ret.setChildren(transformChildren(ret, mChildren));
        return ret;
    }
}
