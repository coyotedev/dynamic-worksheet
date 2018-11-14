package dynamicworksheet.type;

import com.google.gson.annotations.SerializedName;

public enum InputSubType {
    @SerializedName("text")
    Text,
    @SerializedName("phone")
    Phone,
    @SerializedName("email")
    Email,
    @SerializedName("date")
    Date,
    @SerializedName("file")
    File
}
