package net.coyotedev.androidworksheet.uiadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dynamicworksheet.element.IElement;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractTextChanged;

public class ElementTextAdapter implements IElementAdapter {
    @Override
    public View build(IElement element, ViewGroup root, Context ctx) {
        final TextView ret = new TextView(ctx);

        // core ui connection setup
        {
            element.setAdapter(new AdapterBase(ret) {
                @Override
                public void onInteract(MessageInteract message) {
                    super.onInteract(message);
                    if (message.getClass().isAssignableFrom(MessageInteractTextChanged.class)) {
                        MessageInteractTextChanged msg = (MessageInteractTextChanged) message;
                        if (!ret.getText().toString().equals(msg.mText)) {
                            ret.setText(msg.mText);
                        }
                    }
                }
            });
        }

        return ret;
    }
}
