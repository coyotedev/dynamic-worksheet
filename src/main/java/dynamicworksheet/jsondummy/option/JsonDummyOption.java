package dynamicworksheet.jsondummy.option;

import com.google.gson.annotations.SerializedName;

import dynamicworksheet.option.Option;

public class JsonDummyOption {
    @SerializedName("label")
    public String mLabel;
    @SerializedName("value")
    public String mValue;

    public Option get() {
        return new Option(mLabel, mValue);
    }
}
