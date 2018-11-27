package dynamicworksheet.jsondummy.validation.validationcase;

import dynamicworksheet.value.IValue;
import dynamicworksheet.element.IElement;
import dynamicworksheet.validation.ValidationRequired;

public class JsonDummyValidationCaseRequired implements IJsonDummyValidationCase {
    @Override
    public IValue getValue(IElement node) {
        return new ValidationRequired(node.getValue(), "error");
    }
}
