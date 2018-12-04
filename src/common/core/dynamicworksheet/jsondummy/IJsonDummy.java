package core.dynamicworksheet.jsondummy;

import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.util.ElementGsonAdapter;
import core.dynamicworksheet.util.ValidationCaseGsonAdapter;
import core.dynamicworksheet.util.ValueGsonAdapter;

/**
 * Интерфейс для всех классов JsonDummy
 * Данный слой служит цели скрытия деталей реализации протокола.
 * Входящий json автоматически укладывается в классы, реализующие этот протокол, используя адаптеры
 * для gson, подробнее см.:
 * {@link ElementGsonAdapter}
 * {@link ValueGsonAdapter}
 * {@link ValidationCaseGsonAdapter}
 */
public interface IJsonDummy {
    /**
     * Метод для преобразования из болванки с данными в ядерный элемент. Связи устанавливаются на
     * этом этапе (при установке ValueSource'ов, подробнее см. {@link dynamicworksheet.value Классы ValueSource})
     */
    IElement getElement(IElement root);
}
