package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.Value.ValueSimple;
import dynamicworksheet.element.ElementBase;
import dynamicworksheet.element.ElementCheckbox;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.type.CheckSubType;

public class JsonDummyCheck extends JsonDummyBase {
    @SerializedName("subType")
    public CheckSubType mSubType;
    @SerializedName("about")
    public String mAbout;
    @SerializedName("defaultValue")
    public String mDefaultValue;
    @SerializedName("mask")
    public String mMask;
    @SerializedName("prefix")
    public String mPrefix;
    @SerializedName("suffix")
    public String mSuffix;
    @SerializedName("label")
    public IJsonDummyValue mLabel;

    @Override
    public IElement getElement(IElement root) {
        ElementCheckbox ret = new ElementCheckbox(root, new ValueSimple<>(false));
        set(ret);
        return ret;
    }

    @Override
    protected void set(ElementBase element) {
        super.set(element);
        ElementCheckbox casted = (ElementCheckbox) element;
        if (mLabel != null) {
            casted.setLabel((String) mLabel.getValue(casted).getValue());
        }
    }
}
