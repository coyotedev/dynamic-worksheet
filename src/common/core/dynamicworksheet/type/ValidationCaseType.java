package core.dynamicworksheet.type;

import com.google.gson.annotations.SerializedName;

/**
 * Тип валидации
 */
public enum ValidationCaseType {
    @SerializedName("required")
    Required,
    @SerializedName("uploadValid")
    Upload,
    @SerializedName("minLength")
    MinLength
}
