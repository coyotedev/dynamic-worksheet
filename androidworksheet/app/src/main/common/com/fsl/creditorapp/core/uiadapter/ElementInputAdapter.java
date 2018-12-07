package com.fsl.creditorapp.core.uiadapter;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import java.util.List;

import core.dynamicworksheet.element.ElementInput;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.message.interact.MessageInteract;
import core.dynamicworksheet.message.interact.MessageInteractFocusChanged;
import core.dynamicworksheet.message.interact.MessageInteractTextChanged;

public class ElementInputAdapter implements IElementAdapter {
    @Override
    public View build(final IElement element, ViewGroup root, final Context ctx) {
        TextInputLayout ret = new TextInputLayout(ctx);
        final AppCompatEditText editText = new AppCompatEditText(ctx);
        ret.addView(editText);
        ret.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        ret.setErrorEnabled(true);
        ElementInput input = (ElementInput) element;
        input.setValidationHandler(new IElement.IValidationHandler() {
            @Override
            public void onPassed() {
                editText.setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.holo_green_light));
                ret.setError("");
            }

            @Override
            public void onError(List<String> errors) {
                String error = TextUtils.join("\n", errors);
                ret.setError(error);
            }
        });
        ret.setHint(input.getPlaceholder());

        // core ui connection setup
        setupCoreConnections(element, ret);

        return ret;
    }

    protected void setupCoreConnections(final IElement element, final TextInputLayout view) {
        element.setAdapter(new AdapterBase(view) {
            @Override
            public void onInteract(MessageInteract message) {
                super.onInteract(message);
                if (message.getClass().isAssignableFrom(MessageInteractTextChanged.class)) {
                    MessageInteractTextChanged msg = (MessageInteractTextChanged) message;
                    String text = msg.getText();
                    if (!view.getEditText().getText().toString().equals(text)) {
                        view.getEditText().setText(text);
                    }
                }
            }
        });

        view.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                element.onInteract(new MessageInteractTextChanged(charSequence.toString()));
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        view.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                element.onInteract(new MessageInteractFocusChanged(b));
            }
        });
    }
}
