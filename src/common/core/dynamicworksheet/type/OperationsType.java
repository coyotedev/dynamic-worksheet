package core.dynamicworksheet.type;

import com.google.gson.annotations.SerializedName;

/**
 * Тип логической операции
 */
public enum OperationsType {
    Undefined,
    @SerializedName("and")
    And,
    @SerializedName("or")
    Or,
    @SerializedName("eq")
    Equal,
    @SerializedName("not")
    Not
}
