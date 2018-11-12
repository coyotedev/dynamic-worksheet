package dynamicworksheet.types;

import com.google.gson.annotations.SerializedName;

public enum UIType {
    @SerializedName("container")
    Container,
    @SerializedName("wizard")
    Wizard,
    @SerializedName("text")
    Text,
    @SerializedName("radio")
    RadioGroup,
    @SerializedName("input")
    Input,
    @SerializedName("date")
    Date,
    @SerializedName("check")
    CheckBox,
    @SerializedName("select")
    Select,
    @SerializedName("file")
    FileUpload,
    @SerializedName("image")
    ImageView,
    @SerializedName("calculator")
    Calculator
}
