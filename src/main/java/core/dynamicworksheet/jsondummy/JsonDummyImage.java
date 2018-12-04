package core.dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.ValueSimple;
import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.ElementImageUrl;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.type.InputSubType;

public class JsonDummyImage extends core.dynamicworksheet.jsondummy.JsonDummyBase {
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
    public core.dynamicworksheet.jsondummy.value.IJsonDummyValue mValue;
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
