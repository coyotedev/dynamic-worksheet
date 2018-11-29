package dynamicworksheet.jsondummy;

import dynamicworksheet.element.ElementBase;
import dynamicworksheet.element.ElementFileUpload;
import dynamicworksheet.element.IElement;
import dynamicworksheet.value.ValueSimple;

public class JsonDummyFile extends JsonDummyInput {

    @Override
    public IElement getElement(IElement root) {
        ElementFileUpload ret = new ElementFileUpload(root, new ValueSimple<>(null));
        set(ret);
        return ret;
    }

    @Override
    protected void set(ElementBase element) {
        ElementFileUpload casted = (ElementFileUpload) element;
        casted.setPlaceholder((String) mPlaceholder.getValue(casted).getValue());
    }
}