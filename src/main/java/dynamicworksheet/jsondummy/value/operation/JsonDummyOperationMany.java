package dynamicworksheet.jsondummy.value.operation;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;

import java.util.List;

public class JsonDummyOperationMany extends JsonDummyOperationBase {
    @SerializedName("values")
    public List<IJsonDummyValue> mValues;
}