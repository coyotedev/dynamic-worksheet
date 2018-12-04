package com.fsl.androidworksheet.core.uiadapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import core.dynamicworksheet.element.ElementInput;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.message.interact.MessageInteract;
import core.dynamicworksheet.message.interact.MessageInteractFocusChanged;
import core.dynamicworksheet.message.interact.MessageInteractTextChanged;
import core.dynamicworksheet.validation.IValidation;

public class ElementInputAdapter implements IElementAdapter {
    @Override
    public View build(final IElement element, ViewGroup root, final Context ctx) {
        final EditText ret = new EditText(ctx);
        ElementInput input = (ElementInput) element;
        input.setValidationHandler(new IValidation.ValidationHandler() {
            @Override
            public void onPassed() {
                ret.setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.holo_green_light));
            }

            @Override
            public void onError(String error) {
                ret.setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.holo_red_light));
            }
        });
        ret.setHint(input.getPlaceholder());

        // core ui connection setup
        setupCoreConnections(element, ret);

        return ret;
    }

    protected void setupCoreConnections(final IElement element, final EditText view) {
        element.setAdapter(new AdapterBase(view) {
            @Override
            public void onInteract(MessageInteract message) {
                super.onInteract(message);
                if (message.getClass().isAssignableFrom(MessageInteractTextChanged.class)) {
                    MessageInteractTextChanged msg = (MessageInteractTextChanged) message;
                    String text = msg.getText();
                    if (!view.getText().toString().equals(text)) {
                        view.setText(text);
                    }
                }
            }
        });

        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                element.onInteract(new MessageInteractTextChanged(charSequence.toString()));
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                element.onInteract(new MessageInteractFocusChanged(b));
            }
        });
    }
}
