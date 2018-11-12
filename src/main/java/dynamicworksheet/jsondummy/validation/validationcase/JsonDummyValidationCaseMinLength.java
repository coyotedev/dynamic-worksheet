package dynamicworksheet.jsondummy.validation.validationcase;

import com.google.gson.annotations.SerializedName;

public class JsonDummyValidationCaseMinLength implements IJsonDummyValidationCase {
    @SerializedName("minLength")
    public Integer mMinLength;
}
