package net.coyotedev.androidworksheet.uiadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import dynamicworksheet.element.ElementCheckbox;
import dynamicworksheet.element.IElement;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractCheckedChanged;

public class ElementCheckboxAdapter implements IElementAdapter {
    @Override
    public View build(final IElement element, ViewGroup root, Context ctx) {
        final CheckBox ret = new CheckBox(ctx);
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
                        ret.setChecked(msg.mIsChecked);
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
