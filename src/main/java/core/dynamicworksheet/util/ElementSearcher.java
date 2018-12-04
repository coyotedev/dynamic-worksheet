package core.dynamicworksheet.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElementSearcher {
    private core.dynamicworksheet.element.IElement mFromNode;
    private Set<core.dynamicworksheet.element.IElement> mFullySearchedNodes = new HashSet<>();

    public ElementSearcher(core.dynamicworksheet.element.IElement fromNode) {
        mFromNode = fromNode;
    }

    public core.dynamicworksheet.element.IElement search(String id) {
        mFullySearchedNodes.clear();
        core.dynamicworksheet.element.IElement from = mFromNode;
        while (from != null) {
            core.dynamicworksheet.element.IElement result = searchChildren(from, id);
            if (result != null) {
                return result;
            }
            from = from.getRoot();
        }
        return null;
    }

    private core.dynamicworksheet.element.IElement searchChildren(core.dynamicworksheet.element.IElement node, String id) {
        if (node == null) {
            return null;
        }
        if (mFullySearchedNodes.contains(node)) {
            return null;
        }
        if (node.getid().equals(id)) {
            return node;
        }
        List<core.dynamicworksheet.element.IElement> children = node.getChildren();
        for (core.dynamicworksheet.element.IElement it : children) {
            core.dynamicworksheet.element.IElement result = searchChildren(it, id);
            if (result != null) {
                return result;
            }
        }
        mFullySearchedNodes.add(node);
        return null;
    }
}
