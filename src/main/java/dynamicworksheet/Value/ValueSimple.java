package dynamicworksheet.Value;

import io.reactivex.subjects.BehaviorSubject;

public class ValueSimple<T> implements IValue<T> {

    private T mValue;
    private BehaviorSubject<T> mObservable = BehaviorSubject.create();

    public ValueSimple() {}

    public ValueSimple(T value) {
        setValue(value);
    }

    @Override
    public void setValue(T value) {
        mValue = value;
        mObservable.onNext(mValue);
    }

    @Override
    public T getValue() {
        return mValue;
    }

    @Override
    public BehaviorSubject<T> getObservable() {
        return mObservable;
    }
}
