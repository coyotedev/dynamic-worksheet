package dynamicworksheet.element;

import dynamicworksheet.Value.IValue;
import dynamicworksheet.jsondummy.validation.JsonDummyValidation;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.type.UIType;
import dynamicworksheet.validation.IValidation;

import java.util.List;

public interface IElement<T> {

    // интерфейс, реализуемый адаптером UI на конкретной платформе, сигнал CUI (core UI) -> RUI (real UI)
    interface Adapter {
        // необходима осторожность при обращении с выделенным сигналом, т.к. в некоторых случаях
        // можно уйти в бесконечный цикл, реагируя тем же message'м
        void onInteract(MessageInteract message);
    }

    void setId(String id);
    String getid();
    UIType getType();
    void setHidden(IValue<Boolean> value);
    boolean getHidden();
    void setValue(IValue<T> value);
    IValue<T> getValue();

    // дергается при взаимодействии с элементом RUI, сигнал RUI -> CUI
    void onInteract(MessageInteract message);

    IElement getRoot();

    void setAdapter(Adapter adapter);
    Adapter getAdapter();

    void setValidationHandler(IValidation.ValidationHandler handler);
    IValidation.ValidationHandler getValidationHandler();

    void addChild(IElement child);
    void setChildren(List<IElement> children);
    List<IElement> getChildren();

    void setValidations(List<JsonDummyValidation> validations);
    boolean checkValid();
}
