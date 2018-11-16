package dynamicworksheet.Value;

public interface IValue<T> {
    void setValue(T value);
    T getValue();
}
