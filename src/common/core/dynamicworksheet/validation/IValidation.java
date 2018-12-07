package core.dynamicworksheet.validation;

import core.dynamicworksheet.value.IValue;

/**
 * Интерфейс валидаций
 * Валидации используют ValueSource {@link IValue} для определения значения
 * своего состояния.
 */
public interface IValidation {

    boolean check();
    boolean isPassed();
    String getError();
}
