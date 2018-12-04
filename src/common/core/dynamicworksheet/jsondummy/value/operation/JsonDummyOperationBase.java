package core.dynamicworksheet.jsondummy.value.operation;

import com.google.gson.annotations.SerializedName;
import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.jsondummy.value.IJsonDummyValue;
import core.dynamicworksheet.type.OperationsType;

public class JsonDummyOperationBase implements IJsonDummyValue {
    @SerializedName("type")
    public OperationsType mType;

    @Override
    public IValue getValue(IElement node) {
        return null;
    }
}
