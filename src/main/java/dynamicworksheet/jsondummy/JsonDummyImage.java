package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
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
}
