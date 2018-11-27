package dynamicworksheet.jsondummy.validation.validationcase;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.value.IValue;
import dynamicworksheet.element.IElement;
import dynamicworksheet.validation.ValidationUpload;

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

    @Override
    public IValue getValue(IElement node) {
        ValidationUpload.FileParams ref = new ValidationUpload.FileParams.Builder()
                .setFileSize(mMaxSize)
                .setExtensions(mExtensions)
                .setMinWidth(mMinWidth)
                .setMaxWidth(mMaxWidth)
                .setMinHeight(mMinHeight)
                .setMaxHeight(mMaxHeight)
                .build();
        return new ValidationUpload(node.getValue(), ref, "error");
    }
}
