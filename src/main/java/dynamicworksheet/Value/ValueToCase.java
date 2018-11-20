package dynamicworksheet.Value;

public class ValueToCase extends ValueSimple<String> {
    private Type mType;

    public ValueToCase(Type type) throws IllegalArgumentException {
        if (type == Type.Undefined) {
            throw new IllegalArgumentException("Cannot create ValueToCase: " + type);
        }
        mType = type;
    }

    @Override
    public void setValue(String value) {
        if (value != null) {
            switch (mType) {
                case ToLower:
                    value = value.toLowerCase();
                    break;
                case ToUpper:
                    value = value.toUpperCase();
                    break;
            }
        }
        super.setValue(value);
    }

    public enum Type {
        Undefined,
        ToUpper,
        ToLower
    }
}
