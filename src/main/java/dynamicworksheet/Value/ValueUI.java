package dynamicworksheet.Value;

import dynamicworksheet.util.mutablevalue.MutableValue;

public class ValueUI<T> implements IValue<T> {
    private T mValue;

    @Override
    public void setValue(T value) {
        mValue = value;
    }

    @Override
    public T getValue() {
        return mValue;
    }
}
