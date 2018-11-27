package dynamicworksheet.validation;

/**
 * Интерфейс валидаций
 * Валидации используют ValueSource {@link dynamicworksheet.value.IValue} для определения значения
 * своего состояния.
 */
public interface IValidation {
    /**
     * Интерфейс обработчика результата валидации, реализуется на стороне адаптера RUI
     */
    abstract class ValidationHandler {
        public abstract void onPassed();
        public abstract void onError(String error);
    }

    boolean check(ValidationHandler handler);
    boolean isPassed();
    String getError();
}
