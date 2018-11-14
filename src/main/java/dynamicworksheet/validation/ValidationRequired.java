package dynamicworksheet.validation;

public class ValidationRequired extends ValidationBase {

    private final Object mObject;

    public ValidationRequired(Object obj, String error) {
        super(error);
        mObject = obj;
    }

    @Override
    public boolean isPassed() {
        return mObject != null;
    }
}
