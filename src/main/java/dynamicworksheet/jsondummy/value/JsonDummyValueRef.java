package dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;

public class JsonDummyValueRef implements IJsonDummyValue {
    @SerializedName("ref")
    public final String mValue = "";
}
