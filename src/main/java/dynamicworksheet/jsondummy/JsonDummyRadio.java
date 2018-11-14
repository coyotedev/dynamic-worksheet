package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.option.JsonDummyOption;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.type.RadioSubType;
import dynamicworksheet.type.RadioType;

import java.util.List;

public class JsonDummyRadio extends JsonDummyBase {
    @SerializedName("bindPath")
    public String mBindPath;
    @SerializedName("kind")
    public RadioType mType;
    @SerializedName("subType")
    public RadioSubType mSubType;
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
    @SerializedName("options")
    public List<JsonDummyOption> mOptions;
}
