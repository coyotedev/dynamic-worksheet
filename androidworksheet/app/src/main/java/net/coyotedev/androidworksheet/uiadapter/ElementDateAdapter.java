package net.coyotedev.androidworksheet.uiadapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dynamicworksheet.element.ElementDate;
import dynamicworksheet.element.IElement;

public class ElementDateAdapter extends ElementInputAdapter {
    @Override
    public View build(IElement element, ViewGroup root, Context ctx) {
        final EditText ret = new EditText(ctx);
        final ElementDate date = (ElementDate) element;
        ret.setFocusable(false);
        ret.setHint(date.getPlaceholder());
        ret.setLongClickable(false);

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
                    ret.setText(format.format(userChoise.getTime()));
                }
            }, year, month, day);

            ret.setOnClickListener(new View.OnClickListener() {
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
