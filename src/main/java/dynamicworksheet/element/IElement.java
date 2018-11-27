package dynamicworksheet.element;

import dynamicworksheet.value.IValue;
import dynamicworksheet.jsondummy.validation.JsonDummyValidation;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.type.UIType;
import dynamicworksheet.validation.IValidation;
import io.reactivex.annotations.Nullable;

import java.util.List;


/**
 * Интерфейс, представляющий элемент.
 *
 * Id - уникальный идентификатор элемента, используется для установки связей между элементами.
 *
 * @param <T>Тип, определяющий внутренний основной тип, с которым работает элемент,
 *           например - IElement<String> означает, что новый элемент представляет
 *           строковое значение, таким элементом может являться Label, TextInput и подобное.
 */
public interface IElement<T> {
    /**
     * Интерфейс, реализуемый адаптером UI на конкретной платформе, сигнал CUI (core UI) -> RUI (real UI)
     */
    interface Adapter {
        /**
         * Необходима осторожность при обращении с выделенным сигналом, т.к. в некоторых случаях
         * можно уйти в бесконечный цикл, реагируя тем же message'м.
         * @param message {@link dynamicworksheet.message.interact.MessageInteract}
         */
        void onInteract(MessageInteract message);
    }
    void setId(String id);
    String getid();
    /**
     * Возвращает тип элемента
     * @return {@link dynamicworksheet.type.UIType}
     */
    UIType getType();
    void setHidden(IValue<Boolean> value);
    boolean getHidden();
    /**
     * Сеттер для основного ValueSource элемента. Используется только внутренними классами пакета
     * {@link dynamicworksheet.jsondummy}. Следует избегать ручной переустановки ValueSource,
     * так как это может повредить логику связей между элементами.
     * @param value {@link dynamicworksheet.value Классы ValueSource}
     */
    void setValue(IValue<T> value);
    /**
     * Геттер для основного ValueSource элемента. Используя этот метод можно получить основное
     * значение элемента, хранимое ValueSource'ом, обратившись к методу {@link IValue#getValue()}
     * @return {@link dynamicworksheet.value Классы ValueSource}
     */
    IValue<T> getValue();
    /**
     * Вызывается адаптером RUI при взаимодействии с элементом RUI, сигнал RUI -> CUI
     * @param message {@link dynamicworksheet.message.interact.MessageInteract}
     */
    void onInteract(MessageInteract message);
    /**
     * Возвращает родителя для данного элемента
     * @return {@link IElement}
     */
    @Nullable
    IElement getRoot();
    void setAdapter(Adapter adapter);
    Adapter getAdapter();
    /**
     * Сеттер для обработчика валидаций для данного элемента, устанавливается адаптером RUI.
     * @param handler {@link IValidation.ValidationHandler}
     */
    void setValidationHandler(IValidation.ValidationHandler handler);
    IValidation.ValidationHandler getValidationHandler();

    void addChild(IElement child);
    void setChildren(List<IElement> children);
    List<IElement> getChildren();

    void setValidations(List<JsonDummyValidation> validations);
    boolean checkValid();
}
