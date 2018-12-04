package core.dynamicworksheet.jsondummy;

import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.ElementFileUpload;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.value.ValueSimple;

public class JsonDummyFile extends core.dynamicworksheet.jsondummy.JsonDummyInput {

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