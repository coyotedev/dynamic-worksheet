package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.validation.JsonDummyValidation;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;

import java.util.List;

public class JsonDummyBase implements IJsonDummy {
    @SerializedName("id")
    public final String mId = "";
    @SerializedName("validations")
    public List<JsonDummyValidation> mValidations;
    @SerializedName("hidden")
    public IJsonDummyValue mHidden;
}
