package dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.Value.IValue;
import dynamicworksheet.Value.ValueSimple;
import dynamicworksheet.element.IElement;

public class JsonDummyValueConst implements IJsonDummyValue {
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
