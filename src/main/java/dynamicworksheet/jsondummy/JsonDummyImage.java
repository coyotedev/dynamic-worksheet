package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import dynamicworksheet.Value.ValueSimple;
import dynamicworksheet.element.ElementBase;
import dynamicworksheet.element.ElementImageUrl;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.type.InputSubType;

public class JsonDummyImage extends JsonDummyBase {
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
    @SerializedName("value")
    public IJsonDummyValue mValue;
    @SerializedName("width")
    public String mWidth;
    @SerializedName("height")
    public String mHeight;

    @Override
    public IElement getElement(IElement root) {
        ElementImageUrl ret = new ElementImageUrl(root, new ValueSimple<>(""));
        set(ret);
        return ret;
    }

    @Override
    protected void set(ElementBase element) {
        super.set(element);
        ElementImageUrl casted = (ElementImageUrl) element;
        if (mValue != null) {
            casted.setValue(mValue.getValue(casted));
        }
        casted.setSize(new ElementImageUrl.Size(Integer.parseInt(mWidth), Integer.parseInt(mHeight)));
    }
}
