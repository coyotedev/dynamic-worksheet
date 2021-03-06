package core.dynamicworksheet.type;

import com.google.gson.annotations.SerializedName;

/**
 * Тип ValueSource
 */
public enum ValueType {
    @SerializedName("const")
    Const,
    @SerializedName("i18n")
    I18N,
    @SerializedName("model")
    Model,
    @SerializedName("value")
    Ref,
    @SerializedName("and")
    And,
    @SerializedName("or")
    Or,
    @SerializedName("not")
    Not,
    @SerializedName("eq")
    Equal
}
