package dynamicworksheet.Value;

import dynamicworksheet.element.IElement;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ValueReference<T> implements IValue<T> {

    private T mValue;

    public ValueReference(IElement reference, T defaultValue) {
        mValue = defaultValue;
        reference.getObservable().subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                mValue = (T) o;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setValue(T value) {

    }

    @Override
    public T getValue() {
        return mValue;
    }
}
