package dynamicworksheet.element;

import dynamicworksheet.Value.IValue;
import dynamicworksheet.jsondummy.validation.JsonDummyValidation;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.validation.MessageValidation;
import dynamicworksheet.type.UIType;
import dynamicworksheet.validation.IValidation;

import java.util.List;

public interface IElement<T> {

    // интерфейс, реализуемый адаптером UI на конкретной платформе, сигнал CUI (core UI) -> RUI (real UI)
    interface Adapter {
        void onInteract(MessageInteract message);
    }

    void setId(String id);
    String getid();
    UIType getType();
    void setHidden(IValue<Boolean> value);
    boolean getHidden();
    void setValue(IValue<T> value);
    IValue<T> getValue();

    // дергается при взаимодействии человека с элементом RUI, сигнал RUI -> CUI
    void onInteract(MessageInteract message);

    IElement getRoot();

    void setAdapter(Adapter adapter);
    void setValidationHandler(IValidation.ValidationHandler handler);

    void addChild(IElement child);
    void setChildren(List<IElement> children);
    List<IElement> getChildren();

    void setValidations(List<JsonDummyValidation> validations);
    boolean checkValid();
}
