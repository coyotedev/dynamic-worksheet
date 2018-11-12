package dynamicworksheet.types;

import com.google.gson.annotations.SerializedName;

public enum ValidationCaseType {
    @SerializedName("required")
    Required,
    @SerializedName("uploadValid")
    Upload,
    @SerializedName("minLength")
    MinLength
}
