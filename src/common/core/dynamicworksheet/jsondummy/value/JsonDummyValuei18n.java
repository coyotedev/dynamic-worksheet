package core.dynamicworksheet.jsondummy.value;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.value.ValueI18N;
import core.dynamicworksheet.element.IElement;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class JsonDummyValuei18n implements core.dynamicworksheet.jsondummy.value.IJsonDummyValue {
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

    @Override
    public IValue getValue(IElement node) {
        return new ValueI18N(mValue);
    }
}
