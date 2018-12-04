package core.dynamicworksheet.jsondummy.value.operation;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.value.ValueLogicalOperation;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.jsondummy.value.IJsonDummyValue;

import java.util.ArrayList;

public class JsonDummyOperationOne extends core.dynamicworksheet.jsondummy.value.operation.JsonDummyOperationBase {
    @SerializedName("value")
    public IJsonDummyValue mValue;

    @Override
    public IValue getValue(IElement node) {
        return new ValueLogicalOperation
                (
                        mType,
                        new ArrayList<IValue<Boolean>>() {{
                            add(mValue.getValue(node));
                        }}
                );
    }
}
