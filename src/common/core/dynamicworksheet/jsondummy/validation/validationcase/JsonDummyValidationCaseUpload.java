package core.dynamicworksheet.jsondummy.validation.validationcase;

import com.google.gson.annotations.SerializedName;
import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.element.IElement;

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
        core.dynamicworksheet.validation.ValidationUpload.FileRefParams ref = new core.dynamicworksheet.validation.ValidationUpload.FileRefParams.Builder()
                .setFileSize(mMaxSize)
                .setExtensions(mExtensions)
                .setMinWidth(mMinWidth)
                .setMaxWidth(mMaxWidth)
                .setMinHeight(mMinHeight)
                .setMaxHeight(mMaxHeight)
                .build();
        return new core.dynamicworksheet.validation.ValidationUpload(node.getValue(), ref, "error");
    }
}
