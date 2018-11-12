package dynamicworksheet;

import dynamicworksheet.bundles.IBundle;
import dynamicworksheet.bundles.validation.ValidationBundleBase;
import dynamicworksheet.types.UIType;

import java.util.Map;

public interface IElement {

    // интерфейс, реализуемый адаптером UI на конкретной платформе, сигнал CUI (core UI) -> RUI (real UI)
    interface Adapter {
        void onInteract(IBundle bundle);
        void onValidation(ValidationBundleBase validation); // возможно слияние с onInteract ?..
    }

    String getid();
    UIType getType();

    // дергается при взаимодействии человека с элементом RUI, сигнал RUI -> CUI
    void onInteract(IBundle bundle);

    void addChild(IElement child);
    Map<String, IElement> getChilds();
}
