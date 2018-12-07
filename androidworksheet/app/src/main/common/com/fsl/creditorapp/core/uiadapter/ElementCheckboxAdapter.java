package com.fsl.creditorapp.core.uiadapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import core.dynamicworksheet.element.ElementCheckbox;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.message.interact.MessageInteract;
import core.dynamicworksheet.message.interact.MessageInteractCheckedChanged;

public class ElementCheckboxAdapter implements IElementAdapter {
    @Override
    public View build(final IElement element, ViewGroup root, Context ctx) {
        final AppCompatCheckBox ret = new AppCompatCheckBox(ctx);
        ElementCheckbox checkbox = (ElementCheckbox) element;
        ret.setChecked(checkbox.getValue().getValue());
        ret.setText(checkbox.getLabel());
        // core ui connection setup
        {
            element.setAdapter(new AdapterBase(ret) {
                @Override
                public void onInteract(MessageInteract message) {
                    super.onInteract(message);
                    if (message.getClass().isAssignableFrom(MessageInteractCheckedChanged.class)) {
                        MessageInteractCheckedChanged msg = (MessageInteractCheckedChanged) message;
                        ret.setChecked(msg.isChecked());
                    }
                }
            });

            ret.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    element.onInteract(new MessageInteractCheckedChanged(b));
                }
            });
        }

        return ret;
    }
}
