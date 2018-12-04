package core.dynamicworksheet.option;

/**
 * Опция - вариант выбора для Selectable (Radio, Select),
 * {@link dynamicworksheet.element.ElementSelectable}
 */
public class Option {
    private String mLabel;
    private String mValue;

    public Option(String label, String value) {
        mLabel = label;
        mValue = value;
    }

    public String getLabel() {
        return mLabel;
    }

    public String getValue() {
        return mValue;
    }
}
