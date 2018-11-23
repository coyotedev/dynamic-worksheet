package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import dynamicworksheet.element.ElementBase;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.validation.JsonDummyValidation;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;

import java.util.List;

public class JsonDummyBase implements IJsonDummy {
    @SerializedName("id")
    public String mId = "";
    @SerializedName("validations")
    public List<JsonDummyValidation> mValidations;
    @SerializedName("hidden")
    public IJsonDummyValue mHidden;

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
