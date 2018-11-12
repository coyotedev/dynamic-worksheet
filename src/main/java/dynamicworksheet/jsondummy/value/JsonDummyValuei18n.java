package dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;

public class JsonDummyValuei18n implements IJsonDummyValue {
    class I18N {
        @SerializedName("ru")
        public final String mVariantRU = "";
        @SerializedName("en")
        public final String mVariantEN = "";
    }

    @SerializedName("i18n")
    public final I18N mValue = new I18N();
}
