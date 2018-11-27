package dynamicworksheet.jsondummy;

import dynamicworksheet.element.IElement;

/**
 * Интерфейс для всех классов JsonDummy
 * Данный слой служит цели скрытия деталей реализации протокола.
 * Входящий json автоматически укладывается в классы, реализующие этот протокол, используя адаптеры
 * для gson, подробнее см.:
 * {@link dynamicworksheet.util.ElementGsonAdapter}
 * {@link dynamicworksheet.util.ValueGsonAdapter}
 * {@link dynamicworksheet.util.ValidationCaseGsonAdapter}
 */
public interface IJsonDummy {
    /**
     * Метод для преобразования из болванки с данными в ядерный элемент. Связи устанавливаются на
     * этом этапе (при установке ValueSource'ов, подробнее см. {@link dynamicworksheet.value Классы ValueSource})
     */
    IElement getElement(IElement root);
}
