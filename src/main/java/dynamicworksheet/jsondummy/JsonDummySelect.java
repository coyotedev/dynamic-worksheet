package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.option.JsonDummyOption;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.types.SelectSubType;

import java.util.List;

public class JsonDummySelect extends JsonDummyBase {
    @SerializedName("subType")
    public SelectSubType mSubType;
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
