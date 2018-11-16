package dynamicworksheet.Value;

import io.reactivex.subjects.BehaviorSubject;

public interface IValue<T> {
    void setValue(T value);
    T getValue();

    BehaviorSubject<T> getObservable();
}
