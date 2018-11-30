package dynamicworksheet.value;

import dynamicworksheet.type.OperationsType;
import io.reactivex.functions.Consumer;

import java.util.List;

/**
 * ValueSource - логическая операция
 * Представляет значение, вычисленное по всем операндам
 * Значение обновляется в конструкторе и каждый раз при изменении значений операндов
 */
public class ValueLogicalOperation extends ValueSimple<Boolean> {
    private OperationsType mType;
    private List<dynamicworksheet.value.IValue<Boolean>> mOperands;

    public ValueLogicalOperation(OperationsType type, List<dynamicworksheet.value.IValue<Boolean>> operands) throws IllegalArgumentException {
        if (type == OperationsType.Undefined) {
            throw new IllegalArgumentException("Cannot create operation: " + type);
        }
        mType = type;
        mOperands = operands;
        for (dynamicworksheet.value.IValue it : operands) {
            it.getObservable().subscribe(new Consumer() {
                @Override
                public void accept(Object o) throws IllegalArgumentException {
                    computeValue();
                }
            });
        }
    }

    private void computeValue() throws IllegalArgumentException {
        switch (mType) {
            case And: {
                boolean ret = true;
                for (dynamicworksheet.value.IValue<Boolean> it : mOperands) {
                    ret &= it.getValue();
                }
                setValue(ret);
                break;
            }
            case Or: {
                boolean ret = false;
                for (dynamicworksheet.value.IValue<Boolean> it : mOperands) {
                    ret |= it.getValue();
                }
                setValue(ret);
                break;
            }
            case Equal: {
                if (mOperands.size() < 1) {
                    throw new IllegalArgumentException("Wrong number of operands in operation " + mType);
                }
                boolean ret = true;
                boolean first = mOperands.get(0).getValue();
                for (int i = 1; i < mOperands.size(); ++i) {
                    if (mOperands.get(i).getValue() != first) {
                        ret = false;
                        break;
                    }
                }
                setValue(ret);
                break;
            }
            case Not: {
                boolean ret = true;
                for (dynamicworksheet.value.IValue<Boolean> it : mOperands) {
                    ret &= it.getValue();
                }
                setValue(!ret);
                break;
            }
        }
    }
}
