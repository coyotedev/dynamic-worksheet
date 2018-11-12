package dynamicworksheet;

import dynamicworksheet.bundles.IBundle;
import dynamicworksheet.types.UIType;

import java.util.Map;

public class ElementBase implements IElement {

    private String mId;
    private UIType mType;
    private Map<String, IElement> mChilds;

    @Override
    public String getid() {
        return mId;
    }

    @Override
    public UIType getType() {
        return mType;
    }

    @Override
    public void onInteract(IBundle bundle) {

    }

    @Override
    public void addChild(IElement child) {

    }

    @Override
    public Map<String, IElement> getChilds() {
        return null;
    }
}
