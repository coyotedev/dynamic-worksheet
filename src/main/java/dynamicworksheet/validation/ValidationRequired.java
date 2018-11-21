package dynamicworksheet.validation;

import dynamicworksheet.Value.IValue;

public class ValidationRequired extends ValidationBase {

    private final IValue mObject;

    public ValidationRequired(IValue obj, String error) {
        super(error);
        mObject = obj;
    }

    @Override
    public boolean isPassed() {
        Object obj = mObject.getValue();
        if (obj != null) {
            if (obj.getClass().isAssignableFrom(String.class)) {
                return !((String) obj).isEmpty();
            }
        }
        return false;
    }
}
