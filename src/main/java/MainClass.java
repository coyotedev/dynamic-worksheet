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
//            Object jsonObj = parser.parse(new FileReader("./src/main/java/jsondata/uidata.json"));
            Object jsonObj = parser.parse(new FileReader("./src/main/java/jsondata/uidata_simple.json"));
            String json = jsonObj.toString();
            IJsonDummy root = gson.fromJson(json, IJsonDummy.class);
            System.out.println(root);

            IElement rootElement = root.getElement(null);
            System.out.println(rootElement);

            IElement checkbox = ((IElement) ((IElement) rootElement.getChildren().get(1)).getChildren().get(0));
            checkbox.getValue().setValue(true);
            System.out.println(checkbox);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        ValueSimple<String> val = new ValueSimple<>();
//        val.setValue("TEST");
//        ElementInput inp1 = new ElementInput(null, val);
//        ElementInput inp2 = new ElementInput(null, new ValueReference<>(inp1.getValue(), "LOL"));
//
//        inp1.getValue().setValue("TEST1");
//        System.out.println(inp2.getValue().getValue());
//
//        inp1.getValue().setValue("TEST2");
//        System.out.println(inp2.getValue().getValue());
//
//        inp1.getValue().setValue("TEST3");
//        System.out.println(inp2.getValue().getValue());
//
//        ValueSimple<Boolean> constVal1 = new ValueSimple<>();
//        constVal1.setValue(true);
//        ValueSimple<Boolean> constVal2 = new ValueSimple<>();
//        constVal2.setValue(true);
//        ValueSimple<Boolean> constVal3 = new ValueSimple<>();
//        constVal3.setValue(true);
//        ValueSimple<Boolean> constVal4 = new ValueSimple<>();
//        constVal4.setValue(true);
//
//        ValueLogicalOperation and1 = new ValueLogicalOperation(OperationsType.And, new ArrayList<IValue<Boolean>>(){{
//            add(constVal1);
//            add(constVal2);
//        }});
//
//        ValueLogicalOperation and2 = new ValueLogicalOperation(OperationsType.And, new ArrayList<IValue<Boolean>>(){{
//            add(constVal3);
//            add(constVal4);
//        }});
//
//        ValueLogicalOperation or = new ValueLogicalOperation(OperationsType.Or, new ArrayList<IValue<Boolean>>(){{
//            add(and1);
//            add(and2);
//        }});
//
//        System.out.println(or.getValue());
//        constVal2.setValue(false);
//        System.out.println(or.getValue());
//        constVal3.setValue(false);
//        System.out.println(or.getValue());
//
//        // ElementSearcher tests
//        {
//            ValueSimple<String> valTest = new ValueSimple<>();
//            valTest.setValue("TEST");
//            ElementInput inpTest1 = new ElementInput(null, valTest);
//            inpTest1.setId("inpTest1");
//
//            ElementInput inpTest11 = new ElementInput(inpTest1, valTest);
//            inpTest11.setId("inpTest11");
//            ElementInput inpTest111 = new ElementInput(inpTest11, valTest);
//            inpTest111.setId("inpTest111");
//            ElementInput inpTest112 = new ElementInput(inpTest11, valTest);
//            inpTest112.setId("inpTest112");
//            ElementInput inpTest1111 = new ElementInput(inpTest111, valTest);
//            inpTest1111.setId("inpTest1111");
//            ElementInput inpTest1112 = new ElementInput(inpTest111, valTest);
//            inpTest1112.setId("inpTest1112");
//            ElementInput inpTest1121 = new ElementInput(inpTest112, valTest);
//            inpTest1121.setId("inpTest1121");
//            ElementInput inpTest1122 = new ElementInput(inpTest112, valTest);
//            inpTest1122.setId("inpTest1122");
//
//            ElementInput inpTest12 = new ElementInput(inpTest1, valTest);
//            inpTest12.setId("inpTest12");
//            ElementInput inpTest121 = new ElementInput(inpTest12, valTest);
//            inpTest121.setId("inpTest121");
//            ElementInput inpTest122 = new ElementInput(inpTest12, valTest);
//            inpTest122.setId("inpTest122");
//            ElementInput inpTest1211 = new ElementInput(inpTest121, valTest);
//            inpTest1211.setId("inpTest1211");
//            ElementInput inpTest1212 = new ElementInput(inpTest121, valTest);
//            inpTest1212.setId("inpTest1212");
//            ElementInput inpTest1221 = new ElementInput(inpTest122, valTest);
//            inpTest1221.setId("inpTest1221");
//            ElementInput inpTest1222 = new ElementInput(inpTest122, valTest);
//            inpTest1222.setId("inpTest1222");
//
////            inpTest1222.setId("FIND");
//
//            ElementSearcher searcher = new ElementSearcher(inpTest121);
//            IElement result = searcher.search("FIND");
//            System.out.println(result);
//        }
    }
}
