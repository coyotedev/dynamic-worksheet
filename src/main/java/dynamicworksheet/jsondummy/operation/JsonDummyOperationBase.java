package dynamicworksheet.jsondummy.operation;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.type.OperationsType;

public class JsonDummyOperationBase implements IJsonDummyValue {
    @SerializedName("type")
    public OperationsType mType;
}
