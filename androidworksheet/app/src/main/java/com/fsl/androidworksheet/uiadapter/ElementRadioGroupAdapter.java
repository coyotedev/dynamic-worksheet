package com.fsl.androidworksheet.uiadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dynamicworksheet.element.ElementSelectable.ElementRadioGroup;
import dynamicworksheet.element.IElement;
import dynamicworksheet.message.interact.MessageInteract;
import dynamicworksheet.message.interact.MessageInteractSelectedChanged;
import dynamicworksheet.message.interact.MessageInteractTextChanged;
import dynamicworksheet.option.Option;

public class ElementRadioGroupAdapter implements IElementAdapter {
    @Override
    public View build(final IElement element, ViewGroup root, Context ctx) {
        RadioGroup ret = new RadioGroup(ctx);
        ElementRadioGroup radioGroup = (ElementRadioGroup) element;

        ret.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        ret.setOrientation(LinearLayout.VERTICAL);

        // title for radioGroup
        final TextView title = new TextView(ctx);
        ret.addView(title);

        // add radioButtons
        List<Option> options = radioGroup.getOptions();
        final List<RadioButton> buttons = new ArrayList<>();
        for (int i = 0; i < options.size(); ++i) {
            RadioButton button = new RadioButton(ctx);
            Option option = options.get(i);
            button.setTag(option.getValue());
            button.setText(option.getLabel());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    element.onInteract(new MessageInteractSelectedChanged((String) view.getTag()));
                }
            });
            ret.addView(button);
            buttons.add(button);
        }

        // core ui connection setup
        {
            element.setAdapter(new AdapterBase(ret) {
                @Override
                public void onInteract(MessageInteract message) {
                    super.onInteract(message);
                    if (message.getClass().isAssignableFrom(MessageInteractTextChanged.class)) {
                        MessageInteractTextChanged msg = (MessageInteractTextChanged) message;
                        title.setText(msg.getText());
                    } else if (message.getClass().isAssignableFrom(MessageInteractSelectedChanged.class)) {
                        MessageInteractSelectedChanged msg = (MessageInteractSelectedChanged) message;
                        for (RadioButton it : buttons) {
                            if (it.getTag().equals(msg.getTag())) {
                                it.setChecked(true);
                            }
                        }
                    }
                }
            });
        }

        return ret;
    }
}
