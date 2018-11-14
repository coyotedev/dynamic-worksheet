package dynamicworksheet.type;

import com.google.gson.annotations.SerializedName;

public enum OperationsType {
    @SerializedName("and")
    And,
    @SerializedName("or")
    Or,
    @SerializedName("eq")
    Equal
}
