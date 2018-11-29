package dynamicworksheet.value;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Базовый ValueSource.
 * Хранит значение без зависимостей.
 *
 * @param <T> {@inheritDoc}
 */
public class ValueSimple<T> implements IValue<T> {

    private T mValue;
    private BehaviorSubject<T> mObservable = BehaviorSubject.create();

    public ValueSimple() {
    }

    public ValueSimple(T value) {
        setValue(value);
    }

    /**
     * Сеттер для хранимого значения. Каждый раз при его изменении Observable данного ValueSource'а
     * будет излучать сигнал с новым значением.
     */
    @Override
    public void setValue(T value) {
        if (value == null) {
            return;
        }
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
