package core.dynamicworksheet.value;

/**
 * ValueSource - первый символ строки к верхнему регистру
 */
public class ValueCapitalize extends core.dynamicworksheet.value.ValueSimple<String> {

    @Override
    public void setValue(String value) {
        if (value != null && !value.isEmpty()) {
            value = value.substring(0,1).toUpperCase() + value.substring(1);
        }
        super.setValue(value);
    }
}
