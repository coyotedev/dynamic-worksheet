package core.dynamicworksheet.validation;

import core.dynamicworksheet.value.ValueSimple;

/**
 * Базовый класс для всех валидаций
 */
public abstract class ValidationBase extends ValueSimple<Boolean> implements IValidation {

    private final String mError;

    ValidationBase(String error) {
        mError = error;
    }

    @Override
    public boolean check() {
        return isPassed();
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
