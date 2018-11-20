package dynamicworksheet.jsondummy.value.operation;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.Value.IValue;
import dynamicworksheet.Value.ValueLogicalOperation;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;

import java.util.ArrayList;

public class JsonDummyOperationBinary extends JsonDummyOperationBase {
    @SerializedName("value")
    public IJsonDummyValue mValue;
    @SerializedName("other")
    public IJsonDummyValue mOther;

    @Override
    public IValue getValue(IElement node) {
        return new ValueLogicalOperation
                (
                        mType,
                        new ArrayList<IValue<Boolean>>() {{
                            add(mValue.getValue(node));
                            add(mOther.getValue(node));
                        }}
                );
    }
}
