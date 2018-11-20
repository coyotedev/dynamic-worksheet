package dynamicworksheet.Value;

public class ValueCapitalize extends ValueSimple<String> {

    @Override
    public void setValue(String value) {
        if (value != null && !value.isEmpty()) {
            value = value.substring(0,1).toUpperCase() + value.substring(1);
        }
        super.setValue(value);
    }
}
