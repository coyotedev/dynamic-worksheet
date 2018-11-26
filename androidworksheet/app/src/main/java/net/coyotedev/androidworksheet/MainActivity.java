package net.coyotedev.androidworksheet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import net.coyotedev.androidworksheet.uiadapter.ElementAdapter;

import java.util.List;

import dynamicworksheet.UIBuilder;
import dynamicworksheet.element.IElement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        ViewGroup rootLayout = findViewById(R.id.id_mainlayout);

        List<IElement> views = UIBuilder.parse(UIBuilder.json);
        for (IElement it : views) {
            View v = ElementAdapter.getInstance().build(it, rootLayout, this);
            rootLayout.addView(v);
        }
    }
}
