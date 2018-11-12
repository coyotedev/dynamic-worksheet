package dynamicworksheet.jsondummy.validation.validationcase;

import com.google.gson.annotations.SerializedName;

public class JsonDummyValidationCaseUpload implements IJsonDummyValidationCase {
    @SerializedName("maxFileSize")
    public String mMaxSize;
    @SerializedName("extensions")
    public String mExtensions;
    @SerializedName("minWidth")
    public Integer mMinWidth;
    @SerializedName("maxWidth")
    public Integer mMaxWidth;
    @SerializedName("minHeight")
    public Integer mMinHeight;
    @SerializedName("maxHeight")
    public Integer mMaxHeight;
}
