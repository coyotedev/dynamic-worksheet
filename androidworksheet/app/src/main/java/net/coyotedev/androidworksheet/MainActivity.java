package net.coyotedev.androidworksheet;

import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import net.coyotedev.androidworksheet.uiadapter.ElementAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.IJsonDummy;
import dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.util.ElementGsonAdapter;
import dynamicworksheet.util.ValidationCaseGsonAdapter;
import dynamicworksheet.util.ValueGsonAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(IJsonDummy.class, new ElementGsonAdapter())
                .registerTypeAdapter(IJsonDummyValue.class, new ValueGsonAdapter())
                .registerTypeAdapter(IJsonDummyValidationCase.class, new ValidationCaseGsonAdapter())
                .create();

        try {
            BufferedReader jsonFile = new BufferedReader(new InputStreamReader(getAssets().open("uidata_simple.json")));
            String json, jsonProcessing;
            StringBuilder jsonBuilder = new StringBuilder();
            while ((jsonProcessing = jsonFile.readLine()) != null) {
                jsonBuilder.append(jsonProcessing);
            }
            json = jsonBuilder.toString();
            IJsonDummy root = gson.fromJson(json, IJsonDummy.class);
            System.out.println(root);

            /************************************************/
            IElement rootElement = root.getElement(null);
            System.out.println(rootElement);
            /************************************************/

            ViewGroup rootLayout = findViewById(R.id.id_mainlayout);
            View v = ElementAdapter.getInstance().build(rootElement, rootLayout, this);
//            View v = getLayoutInflater().inflate(R.layout.v_wizard, rootLayout, false);
            rootLayout.addView(v);

//            IElement checkbox = ((IElement) ((IElement) rootElement.getChildren().get(1)).getChildren().get(0));
//            checkbox.getValue().setValue(true);
//            System.out.println(checkbox);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
