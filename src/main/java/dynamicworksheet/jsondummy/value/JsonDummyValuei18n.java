package dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class JsonDummyValuei18n implements IJsonDummyValue {
    public class I18N {
        @SerializedName("ru")
        public String mVariantRU;
        @SerializedName("en")
        public String mVariantEN;

        public Map<String, String> getMappedValue() {
            Map<String, String> ret = new HashMap<>();
            ret.put(new Locale("ru").getLanguage(), mVariantRU);
            ret.put(new Locale("en").getLanguage(), mVariantEN);
            return ret;
        }
    }

    @SerializedName("i18n")
    public final I18N mValue = new I18N();
}
