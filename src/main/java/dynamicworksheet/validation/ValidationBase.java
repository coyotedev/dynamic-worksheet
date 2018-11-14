package dynamicworksheet.validation;

public abstract class ValidationBase implements IValidation {

    private final String mError;

    ValidationBase(String error) {
        mError = error;
    }

    @Override
    public void check(ValidationHandler handler) {
        if (isPassed()) {
            handler.onPassed();
        } else {
            handler.onError(getError());
        }
    }

    @Override
    public boolean isPassed() {
        return false;
    }

    @Override
    public String getError() {
        return mError;
    }
}
