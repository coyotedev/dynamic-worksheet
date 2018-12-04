package core.dynamicworksheet.jsondummy.validation.validationcase;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.element.IElement;

public class JsonDummyValidationCaseMinLength implements core.dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase {
    @SerializedName("minLength")
    public Integer mMinLength;

    @Override
    public IValue getValue(IElement node) {
        return new core.dynamicworksheet.validation.ValidationMinLength(node.getValue(), mMinLength, "error");
    }
}
