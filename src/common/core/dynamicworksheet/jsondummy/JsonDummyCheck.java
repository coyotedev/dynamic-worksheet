package core.dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.ValueSimple;
import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.ElementCheckbox;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.type.CheckSubType;

public class JsonDummyCheck extends core.dynamicworksheet.jsondummy.JsonDummyBase {
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
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mLabel;

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
