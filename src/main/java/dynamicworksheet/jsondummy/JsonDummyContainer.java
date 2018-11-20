package dynamicworksheet.jsondummy;

import com.google.gson.annotations.SerializedName;
import dynamicworksheet.element.ElementContainer;
import dynamicworksheet.element.IElement;
import dynamicworksheet.type.ContainerLayoutType;

import java.util.ArrayList;
import java.util.List;

public class JsonDummyContainer extends JsonDummyBase {
    @SerializedName("layout")
    public ContainerLayoutType mLayoutType = ContainerLayoutType.Horizontal;
    @SerializedName("c")
    public List<IJsonDummy> mChildren = null;

    List<IElement> transformChildren(IElement root, List<IJsonDummy> dummies) {
        List<IElement> ret = new ArrayList<>();
        for (IJsonDummy it : dummies) {
            ret.add(it.getElement(root));
        }
        return ret;
    }

    @Override
    public IElement getElement(IElement root) {
        ElementContainer ret = new ElementContainer(root);
        ret.setId(mId);
        ret.setLayout(mLayoutType);
        ret.setChildren(transformChildren(ret, mChildren));
        return ret;
    }
}
