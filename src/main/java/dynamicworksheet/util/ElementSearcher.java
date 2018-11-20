package dynamicworksheet.util;

import dynamicworksheet.element.IElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElementSearcher {
    private IElement mFromNode;
    private Set<IElement> mFullySearchedNodes = new HashSet<>();

    public ElementSearcher(IElement fromNode) {
        mFromNode = fromNode;
    }

    public IElement search(String id) {
        mFullySearchedNodes.clear();
        IElement from = mFromNode;
        while (from != null) {
            IElement result = searchChildren(from, id);
            if (result != null) {
                return result;
            }
            from = from.getRoot();
        }
        return null;
    }

    private IElement searchChildren(IElement node, String id) {
        if (mFullySearchedNodes.contains(node)) {
            return null;
        }
        if (node.getid().equals(id)) {
            return node;
        }
        List<IElement> children = node.getChildren();
        for (IElement it : children) {
            IElement result = searchChildren(it, id);
            if (result != null) {
                return result;
            }
        }
        mFullySearchedNodes.add(node);
        return null;
    }
}
