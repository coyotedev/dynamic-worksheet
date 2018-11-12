package dynamicworksheet.jsondummy.operations;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.types.OperationsType;

public class JsonDummyOperationBase implements IJsonDummyValue {
    @SerializedName("type")
    public OperationsType mType;
}
