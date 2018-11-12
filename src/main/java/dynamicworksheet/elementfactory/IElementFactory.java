package dynamicworksheet.elementfactory;

import dynamicworksheet.jsondummy.IJsonDummy;

public interface IElementFactory {
    IElementFactory withDummy(IJsonDummy dummy);
}
