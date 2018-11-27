package dynamicworksheet.option;

/**
 * Опция - вариант выбора для Selectable (Radio, Select),
 * {@link dynamicworksheet.element.ElementSelectable}
 */
public class Option {
    public String mLabel;
    public String mValue;

    public Option(String label, String value) {
        mLabel = label;
        mValue = value;
    }
}
