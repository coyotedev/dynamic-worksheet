package dynamicworksheet.Value;

import dynamicworksheet.jsondummy.value.JsonDummyValuei18n;

import java.util.Locale;

public class ValueI18N extends ValueSimple<String> {
    private JsonDummyValuei18n.I18N mBundle;

    public ValueI18N(JsonDummyValuei18n.I18N bundle) {
        super(bundle.getMappedValue().get(Locale.getDefault().getLanguage()));
        mBundle = bundle;
    }

    @Override
    public String getValue() {
        return mBundle.getMappedValue().get(Locale.getDefault().getLanguage());
    }
}
