package dynamicworksheet.type;

import com.google.gson.annotations.SerializedName;

/**
 * Основной тип элемента, возвращаемый интерфейсом {@link dynamicworksheet.element.IElement}
 * В данный момент используется также в json-описании, генерируемом сервером
 * @see dynamicworksheet.jsondummy
 */
public enum UIType {
    Undefined,
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
