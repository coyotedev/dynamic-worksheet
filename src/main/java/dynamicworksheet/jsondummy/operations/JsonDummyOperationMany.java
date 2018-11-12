package dynamicworksheet.jsondummy.operations;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;

import java.util.List;

public class JsonDummyOperationMany extends JsonDummyOperationBase {
    @SerializedName("values")
    public List<IJsonDummyValue> mValues;
}
