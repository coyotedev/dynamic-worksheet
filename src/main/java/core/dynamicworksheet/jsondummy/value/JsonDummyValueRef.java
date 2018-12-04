package core.dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.value.ValueReference;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.util.ElementSearcher;

public class JsonDummyValueRef implements core.dynamicworksheet.jsondummy.value.IJsonDummyValue {
    @SerializedName("ref")
    public String mValue;

    @Override
    public IValue getValue(IElement node) {
        IElement refElement = new ElementSearcher(node).search(mValue);
        return new ValueReference(refElement.getValue(), refElement.getValue().getValue());
    }
}
