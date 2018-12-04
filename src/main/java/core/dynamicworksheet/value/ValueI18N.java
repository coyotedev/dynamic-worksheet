package core.dynamicworksheet.value;

import java.util.Locale;

/**
 * ValueSource - локализованная строка, возвращает вариант в зависимости от текущей локали девайса.
 */
public class ValueI18N extends core.dynamicworksheet.value.ValueSimple<String> {
    private core.dynamicworksheet.jsondummy.value.JsonDummyValuei18n.I18N mBundle;

    public ValueI18N(core.dynamicworksheet.jsondummy.value.JsonDummyValuei18n.I18N bundle) {
        super(bundle.getMappedValue().get(Locale.getDefault().getLanguage()));
        mBundle = bundle;
    }

    @Override
    public String getValue() {
        return mBundle.getMappedValue().get(Locale.getDefault().getLanguage());
    }
}
