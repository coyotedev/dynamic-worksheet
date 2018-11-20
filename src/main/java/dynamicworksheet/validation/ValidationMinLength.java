package dynamicworksheet.validation;

import dynamicworksheet.Value.IValue;

public class ValidationMinLength extends ValidationBase {
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
