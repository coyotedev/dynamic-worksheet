package core.dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.IElement;

import java.util.List;

public class JsonDummyBase implements core.dynamicworksheet.jsondummy.IJsonDummy {
    @SerializedName("id")
    public String mId = "";
    @SerializedName("validations")
    public List<core.dynamicworksheet.jsondummy.validation.JsonDummyValidation> mValidations;
    @SerializedName("hidden")
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mHidden;

    @Override
    public IElement getElement(IElement root) {
        return null;
    }

    protected void set(ElementBase element) {
        element.setId(mId);
        if (mHidden != null) {
            element.setHidden(mHidden.getValue(element));
        }
        element.setValidations(mValidations);
    }
}
