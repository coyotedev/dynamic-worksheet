package dynamicworksheet.jsondummy.value.operation;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.Value.IValue;
import dynamicworksheet.Value.ValueLogicalOperation;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;

import java.util.ArrayList;
import java.util.List;

public class JsonDummyOperationMany extends JsonDummyOperationBase {
    @SerializedName("values")
    public List<IJsonDummyValue> mValues;

    @Override
    public IValue getValue(IElement node) {
        return new ValueLogicalOperation
                (
                        mType,
                        new ArrayList<IValue<Boolean>>() {{
                            for (IJsonDummyValue it : mValues) {
                                add(it.getValue(node));
                            }
                        }}
                );
    }
}
