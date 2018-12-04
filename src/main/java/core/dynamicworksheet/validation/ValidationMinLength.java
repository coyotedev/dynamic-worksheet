package core.dynamicworksheet.validation;

import core.dynamicworksheet.value.IValue;

/**
 * Валидация по минимальной длине строки
 */
public class ValidationMinLength extends core.dynamicworksheet.validation.ValidationBase {
    private final IValue<String> mValue;
    private final Integer mMinLength;

    public ValidationMinLength(final IValue<String> value, Integer min, String error) {
        super(error);
        mValue = value;
        mMinLength = min;
    }

    @Override
    public boolean isPassed() {
        return mValue.getValue().length() >= mMinLength;
    }
}
