package core.dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;

import core.dynamicworksheet.element.ElementBase;
import core.dynamicworksheet.element.ElementContainer;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.type.ContainerLayoutType;

import java.util.ArrayList;
import java.util.List;

public class JsonDummyContainer extends JsonDummyBase {
    @SerializedName("layout")
    public ContainerLayoutType mLayoutType = ContainerLayoutType.Horizontal;
    @SerializedName("c")
    public List<core.dynamicworksheet.jsondummy.IJsonDummy> mChildren = null;

    List<IElement> transformChildren(IElement root, List<core.dynamicworksheet.jsondummy.IJsonDummy> dummies) {
        List<IElement> ret = new ArrayList<>();
        for (core.dynamicworksheet.jsondummy.IJsonDummy it : dummies) {
            ret.add(it.getElement(root));
        }
        return ret;
    }

    @Override
    public IElement getElement(IElement root) {
        ElementContainer ret = new ElementContainer(root);
        set(ret);
        return ret;
    }

    @Override
    protected void set(ElementBase element) {
        super.set(element);
        ((ElementContainer) element).setLayout(mLayoutType);
        element.setChildren(transformChildren(element, mChildren));
    }
}
