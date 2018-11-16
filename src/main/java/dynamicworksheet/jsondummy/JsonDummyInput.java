package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
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
//        ElementInput ret = new ElementInput(root);
//        ret.setValidations(mValidations);
//        return ret;
        return null;
    }
}
