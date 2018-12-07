package com.fsl.creditorapp.core.uiadapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableRow;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import core.dynamicworksheet.element.ElementDate;
import core.dynamicworksheet.element.IElement;

public class ElementDateAdapter extends ElementInputAdapter {
    @Override
    public View build(IElement element, ViewGroup root, Context ctx) {
        TextInputLayout ret = new TextInputLayout(ctx);
        final EditText editText = new EditText(ctx);
        ret.addView(editText);
        ret.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        ret.setErrorEnabled(true);
        final ElementDate date = (ElementDate) element;
        editText.setFocusable(false);
        ret.setHint(date.getPlaceholder());
        editText.setLongClickable(false);

        date.setValidationHandler(new IElement.IValidationHandler() {
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

        if (ctx instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) ctx;
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            final DatePickerDialog datePicker = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    SimpleDateFormat format = new SimpleDateFormat(/*date.getDateFormat()*/"dd.MM.yyyy", Locale.getDefault()); // TODO: раскомментировать как только сервер начнет быть адекватным
                    Calendar userChoise = Calendar.getInstance();
                    userChoise.set(year, month, dayOfMonth);
                    editText.setText(format.format(userChoise.getTime()));
                }
            }, year, month, day);

            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datePicker.show();
                }
            });
        }

        setupCoreConnections(element, ret);
        return ret;
    }
}
