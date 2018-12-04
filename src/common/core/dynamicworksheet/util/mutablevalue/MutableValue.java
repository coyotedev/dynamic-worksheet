package core.dynamicworksheet.util.mutablevalue;

public class MutableValue<T> {
    private T mValue;

    public MutableValue(T value) {
        mValue = value;
    }

    public T getValue() {
        return mValue;
    }

    public void setValue(T value) {
        mValue = value;
    }
}
