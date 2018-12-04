package core.dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.ValueSimple;
import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.ElementText;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.type.ElementTextType;

public class JsonDummyText extends JsonDummyBase {
    @SerializedName("kind")
    public ElementTextType mKind;
    @SerializedName("value")
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mValue;

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
