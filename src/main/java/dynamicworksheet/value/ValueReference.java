package dynamicworksheet.value;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ValueSource - ссылка
 * Представляет прямую зависимость от значения другого ValueSource'а.
 * @param <T> {@inheritDoc}
 */
public class ValueReference<T> extends dynamicworksheet.value.ValueSimple<T> {

    private T mDefaultValue;

    public ValueReference(dynamicworksheet.value.IValue<T> reference, T defaultValue) {
        mDefaultValue = defaultValue;
        setValue(mDefaultValue);
        subscribe(reference);
    }

    private void subscribe(dynamicworksheet.value.IValue<T> reference) {
        reference.getObservable().subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                if (o != null) {
                    setValue((T) o);
                } else {
                    setValue(mDefaultValue);
                }
            }

            @Override
            public void onError(Throwable e) {
                setValue(mDefaultValue);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
