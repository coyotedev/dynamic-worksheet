package dynamicworksheet.jsondummy.value.operation;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.value.IValue;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.type.OperationsType;

public class JsonDummyOperationBase implements IJsonDummyValue {
    @SerializedName("type")
    public OperationsType mType;

    @Override
    public IValue getValue(IElement node) {
        return null;
    }
}
