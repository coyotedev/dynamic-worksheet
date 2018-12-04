package core.dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.ValueSimple;
import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.ElementInput;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.type.InputSubType;

public class JsonDummyInput extends core.dynamicworksheet.jsondummy.JsonDummyBase {
    @SerializedName("bindPath")
    public String mBindPath;
    @SerializedName("subType")
    public InputSubType mSubType;
    @SerializedName("about")
    public String mAbout;
    @SerializedName("defaultValue")
    public String mDefaultValue;
    @SerializedName("value")
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mValue;
    @SerializedName("mask")
    public String mMask;
    @SerializedName("prefix")
    public String mPrefix;
    @SerializedName("suffix")
    public String mSuffix;
    @SerializedName("verify")
    public Boolean mVerify;
    @SerializedName("maxLength")
    public Integer mMaxLength;
    @SerializedName("label")
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mLabel;
    @SerializedName("title")
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mTitle;
    @SerializedName("placeholder")
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mPlaceholder;
    @SerializedName("help")
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mHelp;

    @Override
    public IElement getElement(IElement root) {
        ElementInput ret = new ElementInput(root, new ValueSimple<>(mDefaultValue));
        set(ret);
        return ret;
    }

    @Override
    protected void set(ElementBase element) {
        super.set(element);
        ElementInput casted = (ElementInput) element;
        if (mValue != null) {
            casted.setValue(mValue.getValue(casted));
        }
        if (mPlaceholder != null) {
            casted.setPlaceholder((String) mPlaceholder.getValue(casted).getValue());
        }
    }
}
