package dynamicworksheet.validation;

import dynamicworksheet.value.ValueSimple;

/**
 * Базовый класс для всех валидаций
 */
public abstract class ValidationBase extends ValueSimple<Boolean> implements IValidation {

    private final String mError;

    ValidationBase(String error) {
        mError = error;
    }

    @Override
    public boolean check(ValidationHandler handler) {
        boolean ret = isPassed();
        if (handler != null) {
            if (ret) {
                handler.onPassed();
            } else {
                handler.onError(getError());
            }
        }
        return ret;
    }

    @Override
    public boolean isPassed() {
        return false;
    }

    @Override
    public String getError() {
        return mError;
    }

    @Override
    public void setValue(Boolean value) {

    }

    @Override
    public Boolean getValue() {
        return isPassed();
    }
}
