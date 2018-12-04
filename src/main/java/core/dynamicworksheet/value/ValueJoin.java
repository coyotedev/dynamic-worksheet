package core.dynamicworksheet.value;

import io.reactivex.functions.Consumer;

import java.util.List;

/**
 * ValueSource - объединение многих строковых источников в одно значение
 */
public class ValueJoin extends core.dynamicworksheet.value.ValueSimple<String> {

    private final List<core.dynamicworksheet.value.IValue<String>> mSubs;

    public ValueJoin(List<core.dynamicworksheet.value.IValue<String>> subs) {
        mSubs = subs;
        for (core.dynamicworksheet.value.IValue<String> it : mSubs) {
            it.getObservable().subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    setValue(join());
                }
            });
        }
    }

    private String join() {
        String ret = null;
        for (core.dynamicworksheet.value.IValue<String> it : mSubs) {
            if (it.getValue() != null) {
                ret += it.getValue();
            }
        }
        return ret;
    }
}
