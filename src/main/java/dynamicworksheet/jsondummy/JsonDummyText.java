package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.types.ElementTextType;

public class JsonDummyText extends JsonDummyBase {
    @SerializedName("kind")
    public ElementTextType mKind;
    @SerializedName("value")
    public IJsonDummyValue mValue;
}
