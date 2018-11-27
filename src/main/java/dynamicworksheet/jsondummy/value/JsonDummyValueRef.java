package dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.value.IValue;
import dynamicworksheet.value.ValueReference;
import dynamicworksheet.element.IElement;
import dynamicworksheet.util.ElementSearcher;

public class JsonDummyValueRef implements IJsonDummyValue {
    @SerializedName("ref")
    public String mValue;

    @Override
    public IValue getValue(IElement node) {
        IElement refElement = new ElementSearcher(node).search(mValue);
        return new ValueReference(refElement.getValue(), refElement.getValue().getValue());
    }
}
