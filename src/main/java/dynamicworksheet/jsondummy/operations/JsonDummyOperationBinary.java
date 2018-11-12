package dynamicworksheet.jsondummy.operations;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;

public class JsonDummyOperationBinary extends JsonDummyOperationBase {
    @SerializedName("value")
    public IJsonDummyValue mValue;
    @SerializedName("other")
    public IJsonDummyValue mOther;
}
