package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.Value.ValueSimple;
import dynamicworksheet.element.ElementInput;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.type.InputSubType;

public class JsonDummyInput extends JsonDummyBase {
    @SerializedName("bindPath")
    public String mBindPath;
    @SerializedName("subType")
    public InputSubType mSubType;
    @SerializedName("about")
    public String mAbout;
    @SerializedName("defaultValue")
    public String mDefaultValue;
    @SerializedName("value")
    public IJsonDummyValue mValue;
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
    public IJsonDummyValue mLabel;
    @SerializedName("title")
    public IJsonDummyValue mTitle;
    @SerializedName("placeholder")
    public IJsonDummyValue mPlaceholder;
    @SerializedName("help")
    public IJsonDummyValue mHelp;

    @Override
    public IElement getElement(IElement root) {
        ElementInput ret = new ElementInput(root, new ValueSimple<>(mDefaultValue));
        ret.setId(mId);
        ret.setHidden(mHidden.getValue(ret));
        if (mValue != null) {
            ret.setValue(mValue.getValue(ret));
        }
        ret.setValidations(mValidations);
        if (mPlaceholder != null) {
            ret.setPlaceholder((String) mPlaceholder.getValue(ret).getValue());
        }
        return ret;
    }
}
