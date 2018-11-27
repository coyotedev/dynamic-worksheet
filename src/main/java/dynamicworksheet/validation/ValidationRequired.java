package dynamicworksheet.validation;

import dynamicworksheet.value.IValue;

/**
 * Валидация по наличию (например, совместно с элементом типа Input
 * {@link dynamicworksheet.element.ElementInput} - валидно, если пользователь осуществил любой ввод)
 */
public class ValidationRequired extends ValidationBase {

    private final IValue mObject;

    public ValidationRequired(IValue obj, String error) {
        super(error);
        mObject = obj;
    }

    @Override
    public boolean isPassed() {
        Object obj = mObject.getValue();
        if (obj != null) {
            if (obj.getClass().isAssignableFrom(String.class)) {
                return !((String) obj).isEmpty();
            }
            return true;
        }
        return false;
    }
}
