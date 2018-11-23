package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import dynamicworksheet.Value.ValueSimple;
import dynamicworksheet.element.ElementBase;
import dynamicworksheet.element.ElementText;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.type.ElementTextType;

public class JsonDummyText extends JsonDummyBase {
    @SerializedName("kind")
    public ElementTextType mKind;
    @SerializedName("value")
    public IJsonDummyValue mValue;

    @Override
    public IElement getElement(IElement root) {
        ElementText ret = new ElementText(root, new ValueSimple<>(""));
        set(ret);
        return ret;
    }

    @Override
    protected void set(ElementBase element) {
        super.set(element);
        if (mValue != null) {
            element.setValue(mValue.getValue(element));
        }
    }
}
