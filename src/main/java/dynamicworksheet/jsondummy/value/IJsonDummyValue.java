package dynamicworksheet.jsondummy.value;


import dynamicworksheet.Value.IValue;
import dynamicworksheet.element.IElement;

public interface IJsonDummyValue {
    IValue getValue(IElement node);
}
