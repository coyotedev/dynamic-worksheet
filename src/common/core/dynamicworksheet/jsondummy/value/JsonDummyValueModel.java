package core.dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.element.IElement;

public class JsonDummyValueModel implements core.dynamicworksheet.jsondummy.value.IJsonDummyValue {
    @SerializedName("path")
    public final String mValue = "";

    @Override
    public IValue getValue(IElement node) {
        return null;
    }
}
