package core.dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.value.ValueSimple;
import core.dynamicworksheet.element.IElement;

public class JsonDummyValueConst implements core.dynamicworksheet.jsondummy.value.IJsonDummyValue {
    @SerializedName("value")
    public String mValue;

    @Override
    public IValue getValue(IElement node) {
        boolean isBool = false;
        if (mValue.equals("true") || mValue.equals("false")) {
            isBool = true;
        }
        return isBool ? new ValueSimple<Boolean>(mValue.equals("true")) : new ValueSimple<String>(mValue);
    }
}
