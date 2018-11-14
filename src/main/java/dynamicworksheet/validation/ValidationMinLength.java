package dynamicworksheet.validation;

import dynamicworksheet.util.mutablevalue.MutableString;

public class ValidationMinLength extends ValidationBase {
    private final MutableString mValue;
    private final Integer mMinLength;

    public ValidationMinLength(final MutableString value, Integer min, String error) {
        super(error);
        mValue = value;
        mMinLength = min;
    }

    @Override
    public boolean isPassed() {
        return mValue.getValue().length() >= mMinLength;
    }
}
