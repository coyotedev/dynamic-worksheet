package dynamicworksheet.jsondummy;

import dynamicworksheet.element.IElement;

public interface IJsonDummy {
    default IElement getElement(IElement root) {
        return null;
    }
}
