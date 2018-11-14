package dynamicworksheet.jsondummy.operation;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;

public class JsonDummyOperationOne extends JsonDummyOperationBase {
    @SerializedName("value")
    public IJsonDummyValue mValue;
}
