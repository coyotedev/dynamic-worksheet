import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import dynamicworksheet.Value.ValueReference;
import dynamicworksheet.Value.ValueUI;
import dynamicworksheet.element.ElementInput;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.IJsonDummy;
import dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.util.*;

import java.io.FileReader;

public class MainClass {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(IJsonDummy.class, new ElementGsonAdapter())
                .registerTypeAdapter(IJsonDummyValue.class, new ValueGsonAdapter())
                .registerTypeAdapter(IJsonDummyValidationCase.class, new ValidationCaseGsonAdapter())
                .create();

        try {
            JsonParser parser = new JsonParser();
            // TODO: JsonElement
            Object jsonObj = parser.parse(new FileReader("./src/main/java/jsondata/uidata_simple.json"));
            String json = jsonObj.toString();
            IJsonDummy root = gson.fromJson(json, IJsonDummy.class);
            System.out.println(root);

            IElement rootElement = root.getElement(null);
            System.out.println(rootElement);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ValueUI<String> val = new ValueUI<>();
        val.setValue("TEST");
        ElementInput inp1 = new ElementInput(null, val);
        ElementInput inp2 = new ElementInput(null, new ValueReference<>(inp1, "LOL"));

        inp1.setValue("TEST1");
        System.out.println(inp2.getValue());

        inp1.setValue("TEST2");
        System.out.println(inp2.getValue());

        inp1.setValue("TEST3");
        System.out.println(inp2.getValue());

    }
}
