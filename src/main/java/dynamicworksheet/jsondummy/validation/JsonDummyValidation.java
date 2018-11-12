package dynamicworksheet.jsondummy.validation;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;

public class JsonDummyValidation {
    @SerializedName("valid")
    public IJsonDummyValidationCase mValid;
    @SerializedName("error")
    public IJsonDummyValue mError;
}
