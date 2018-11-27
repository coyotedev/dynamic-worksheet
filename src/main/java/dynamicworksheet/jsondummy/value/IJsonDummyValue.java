package dynamicworksheet.jsondummy.value;


import dynamicworksheet.value.IValue;
import dynamicworksheet.element.IElement;

public interface IJsonDummyValue {
    IValue getValue(IElement node);
}
