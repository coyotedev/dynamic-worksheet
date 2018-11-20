package dynamicworksheet.jsondummy.validation.validationcase;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.Value.IValue;
import dynamicworksheet.element.IElement;
import dynamicworksheet.validation.ValidationMinLength;

public class JsonDummyValidationCaseMinLength implements IJsonDummyValidationCase {
    @SerializedName("minLength")
    public Integer mMinLength;

    @Override
    public IValue getValue(IElement node) {
        return new ValidationMinLength(node.getValue(), mMinLength, "error");
    }
}
