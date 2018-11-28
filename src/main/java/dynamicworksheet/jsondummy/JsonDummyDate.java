package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import dynamicworksheet.element.ElementBase;
import dynamicworksheet.element.ElementDate;
import dynamicworksheet.element.IElement;
import dynamicworksheet.value.ValueSimple;

public class JsonDummyDate extends JsonDummyInput {
    @SerializedName("dateFormat")
    public String mFormat;

    @Override
    public IElement getElement(IElement root) {
        ElementDate ret = new ElementDate(root, new ValueSimple<>(mDefaultValue));
        set(ret);
        return ret;
    }

    @Override
    protected void set(ElementBase element) {
        super.set(element);
        ElementDate casted = (ElementDate) element;
        casted.setDateFormat(mFormat);
    }
}
