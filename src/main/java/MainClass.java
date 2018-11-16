import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import dynamicworksheet.Value.IValue;
import dynamicworksheet.Value.ValueLogicalOperation;
import dynamicworksheet.Value.ValueReference;
import dynamicworksheet.Value.ValueSimple;
import dynamicworksheet.element.ElementInput;
import dynamicworksheet.element.IElement;
import dynamicworksheet.jsondummy.IJsonDummy;
import dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.type.OperationsType;
import dynamicworksheet.util.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;

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

        ValueSimple<String> val = new ValueSimple<>();
        val.setValue("TEST");
        ElementInput inp1 = new ElementInput(null, val);
        ElementInput inp2 = new ElementInput(null, new ValueReference<>(inp1.getValue(), "LOL"));

        inp1.getValue().setValue("TEST1");
        System.out.println(inp2.getValue().getValue());

        inp1.getValue().setValue("TEST2");
        System.out.println(inp2.getValue().getValue());

        inp1.getValue().setValue("TEST3");
        System.out.println(inp2.getValue().getValue());

        ValueSimple<Boolean> constVal1 = new ValueSimple<>();
        constVal1.setValue(true);
        ValueSimple<Boolean> constVal2 = new ValueSimple<>();
        constVal2.setValue(true);
        ValueSimple<Boolean> constVal3 = new ValueSimple<>();
        constVal3.setValue(true);
        ValueSimple<Boolean> constVal4 = new ValueSimple<>();
        constVal4.setValue(true);

        ValueLogicalOperation and1 = new ValueLogicalOperation(OperationsType.And, new ArrayList<IValue<Boolean>>(){{
            add(constVal1);
            add(constVal2);
        }});

        ValueLogicalOperation and2 = new ValueLogicalOperation(OperationsType.And, new ArrayList<IValue<Boolean>>(){{
            add(constVal3);
            add(constVal4);
        }});

        ValueLogicalOperation or = new ValueLogicalOperation(OperationsType.Or, new ArrayList<IValue<Boolean>>(){{
            add(and1);
            add(and2);
        }});

        System.out.println(or.getValue());
        constVal2.setValue(false);
        System.out.println(or.getValue());
        constVal3.setValue(false);
        System.out.println(or.getValue());
    }
}
