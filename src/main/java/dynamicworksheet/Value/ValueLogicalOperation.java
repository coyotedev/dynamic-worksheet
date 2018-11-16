package dynamicworksheet.Value;

import dynamicworksheet.type.OperationsType;
import io.reactivex.functions.Consumer;

import java.util.List;

public class ValueLogicalOperation extends ValueSimple<Boolean> {
    OperationsType mType;
    List<IValue<Boolean>> mOperands;

    public ValueLogicalOperation(OperationsType type, List<IValue<Boolean>> operands) {
        mType = type;
        mOperands = operands;
        for (IValue it : operands) {
            it.getObservable().subscribe(new Consumer() {
                @Override
                public void accept(Object o) throws Exception {
                    computeValue();
                }
            });
        }
    }

    private void computeValue() {
        switch (mType) {
            case And: {
                boolean ret = true;
                for (IValue<Boolean> it : mOperands) {
                    ret &= it.getValue();
                }
                setValue(ret);
                break;
            }
            case Or: {
                boolean ret = false;
                for (IValue<Boolean> it : mOperands) {
                    ret |= it.getValue();
                }
                setValue(ret);
                break;
            }
        }
    }
}
