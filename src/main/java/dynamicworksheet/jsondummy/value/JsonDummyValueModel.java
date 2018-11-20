package dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.Value.IValue;
import dynamicworksheet.element.IElement;

public class JsonDummyValueModel implements IJsonDummyValue {
    @SerializedName("path")
    public final String mValue = "";

    @Override
    public IValue getValue(IElement node) {
        return null;
    }
}
