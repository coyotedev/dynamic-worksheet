package dynamicworksheet.element;

import dynamicworksheet.bundles.IBundle;
import dynamicworksheet.bundles.validation.ValidationBundleBase;
import dynamicworksheet.jsondummy.validation.JsonDummyValidation;
import dynamicworksheet.type.UIType;
import dynamicworksheet.validation.IValidation;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

import java.util.List;

public interface IElement<T> {

    // интерфейс, реализуемый адаптером UI на конкретной платформе, сигнал CUI (core UI) -> RUI (real UI)
    interface Adapter {
        void onInteract(IBundle bundle);
        void onValidation(ValidationBundleBase validation); // possible FIXME: возможно слияние с onInteract ?..
    }

    void setId(String id);
    String getid();
    UIType getType();
    void setValue(T value);
    T getValue();

    // дергается при взаимодействии человека с элементом RUI, сигнал RUI -> CUI
    void onInteract(IBundle bundle);

    IElement getRoot();

    void addChild(IElement child);
    void setChildren(List<IElement> children);
    List<IElement> getChildren();

    void setValidations(List<JsonDummyValidation> validations);
    List<IValidation> getValidations();

    BehaviorSubject<T> getObservable();
}
