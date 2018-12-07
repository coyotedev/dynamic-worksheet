package com.fsl.creditorapp.core.uiadapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;

import java.util.List;

import core.dynamicworksheet.element.ElementContainer;
import core.dynamicworksheet.element.IElement;

public class ElementContainerAdapter implements IElementAdapter {
    @Override
    public View build(IElement element, ViewGroup root, Context ctx) {
        LinearLayout ret = new LinearLayout(ctx);
        ret.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT/*, 1f*/));
        switch (((ElementContainer) element).getLayout()) {
            case Horizontal:
                ret.setOrientation(LinearLayout.HORIZONTAL);
                break;
            case Vertical:
                ret.setOrientation(LinearLayout.VERTICAL);
                break;
        }
        List<IElement> children = element.getChildren();
        for (int i = 0; i < children.size(); ++i) {
            if (children.get(i) != null) {
                View v = ElementAdapter.getInstance().build(children.get(i), ret, ctx);
                ret.addView(v);
            }
        }
        return ret;
    }
}
