package com.fsl.creditorapp.core.uiadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.fsl.creditorapp.core.uiadapter.elementfileupload.ElementFileUploadAdapter;

import core.dynamicworksheet.element.IElement;

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
            case Select:
                return new ElementSelectAdapter().build(element, root, ctx);
            case ImageView:
                return new ElementImageUrlAdapter().build(element, root, ctx);
            case Date:
                return new ElementDateAdapter().build(element, root, ctx);
            case FileUpload:
                return new ElementFileUploadAdapter().build(element, root, ctx);
        }
        return null;
    }
}
