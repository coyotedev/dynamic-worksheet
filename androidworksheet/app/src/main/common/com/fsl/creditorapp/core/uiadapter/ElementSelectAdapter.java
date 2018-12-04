package com.fsl.creditorapp.core.uiadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.fsl.creditorapp.R;

import java.lang.reflect.Method;
import java.util.List;

import core.dynamicworksheet.element.ElementSelectable.ElementSelect;
import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.message.interact.MessageInteract;
import core.dynamicworksheet.message.interact.MessageInteractSelectedChanged;
import core.dynamicworksheet.option.Option;

public class ElementSelectAdapter implements IElementAdapter {
    class TitleSpinner extends AppCompatSpinner {
        TitleSpinner(Context ctx) {
            super(ctx);
        }

        @Override
        public void setAdapter(SpinnerAdapter adapter) {
            super.setAdapter(adapter);
            try {
                final Method m = AdapterView.class.getDeclaredMethod(
                        "setNextSelectedPositionInt",int.class);
                m.setAccessible(true);
                m.invoke(this,-1);

                final Method n = AdapterView.class.getDeclaredMethod(
                        "setSelectedPositionInt",int.class);
                n.setAccessible(true);
                n.invoke(this,-1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class OptionsAdapter extends ArrayAdapter<Option> {
        private String mTitle = "";

        OptionsAdapter(Context ctx, List<Option> list) {
            super(ctx, 0, list);
        }

        public void setTitle(@NonNull String title) {
            mTitle = title;
        }

        int getPositionByTag(String tag) {
            for (int i = 0; i < getCount(); ++i) {
                Option item = getItem(i);
                if (item != null && item.getValue().equals(tag)) {
                    return i;
                }
            }
            return -1;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View ret = convertView;
            if (ret == null) {
                ret = LayoutInflater.from(getContext()).inflate(R.layout.v_spinner_item, parent, false);
            }
            TextView text = ret.findViewById(R.id.v_spinner_text);
            if (position >= 0) {
                Option option = getItem(position);
                assert (text != null);
                if (option != null) {
                    text.setText(option.getLabel());
                    ret.setTag(option.getValue());
                }
            } else {
                text.setText(mTitle);
            }
            return ret;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getView(position, convertView, parent);
        }
    }

    @Override
    public View build(final IElement element, ViewGroup root, Context ctx) {
        final TitleSpinner ret = new TitleSpinner(ctx);
        ElementSelect select = (ElementSelect) element;

        // choices list set
        List<Option> options = select.getOptions();
        final OptionsAdapter adapter = new OptionsAdapter(ctx, options);
        adapter.setTitle(select.getLabel());
        ret.setAdapter(adapter);

        ret.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                element.onInteract(new MessageInteractSelectedChanged((String) view.getTag()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        element.setAdapter(new AdapterBase(ret) {
            @Override
            public void onInteract(MessageInteract message) {
                super.onInteract(message);
                if (message.getClass().isAssignableFrom(MessageInteractSelectedChanged.class)) {
                    MessageInteractSelectedChanged msg = (MessageInteractSelectedChanged) message;
                    ret.setSelection(adapter.getPositionByTag(msg.getTag()));
                }
            }
        });

        return ret;
    }
}
