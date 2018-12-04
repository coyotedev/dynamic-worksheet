package core.dynamicworksheet.jsondummy.validation;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.jsondummy.value.IJsonDummyValue;

public class JsonDummyValidation {
    @SerializedName("valid")
    public core.dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase mValid;
    @SerializedName("error")
    public IJsonDummyValue mError;
}
