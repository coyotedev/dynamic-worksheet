package net.coyotedev.androidworksheet.uiadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import dynamicworksheet.element.IElement;

public class ElementAdapter implements IElementAdapter {
    private static ElementAdapter INSTANCE;

    public static ElementAdapter getInstance() {
        if (INSTANCE == null) {
            synchronized (ElementAdapter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ElementAdapter();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public View build(IElement element, ViewGroup root, Context ctx) {
        switch (element.getType()) {
            case Container:
                return new ElementContainerAdapter().build(element, root, ctx);
            case Wizard:
                return new ElementWizardAdapter().build(element, root, ctx);
            case Input:
                return new ElementInputAdapter().build(element, root, ctx);
            case CheckBox:
                return new ElementCheckboxAdapter().build(element, root, ctx);
            case Text:
                return new ElementTextAdapter().build(element, root, ctx);
            case RadioGroup:
                return new ElementRadioGroupAdapter().build(element, root, ctx);
        }
        return null;
    }
}
