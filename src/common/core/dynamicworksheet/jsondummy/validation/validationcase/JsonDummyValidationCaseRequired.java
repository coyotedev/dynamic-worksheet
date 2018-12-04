package core.dynamicworksheet.jsondummy.validation.validationcase;

import core.dynamicworksheet.value.IValue;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.validation.ValidationRequired;

public class JsonDummyValidationCaseRequired implements core.dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase {
    @Override
    public IValue getValue(IElement node) {
        return new ValidationRequired(node.getValue(), "error");
    }
}
