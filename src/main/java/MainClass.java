import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import dynamicworksheet.jsondummy.IJsonDummy;
import dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase;
import dynamicworksheet.jsondummy.value.IJsonDummyValue;
import dynamicworksheet.util.ElementGsonAdapter;
import dynamicworksheet.util.ValidationCaseGsonAdapter;
import dynamicworksheet.util.ValueGsonAdapter;

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
            Object jsonObj = parser.parse(new FileReader("./src/main/java/jsondata/uidata.json"));
            String json = jsonObj.toString();
            IJsonDummy root = gson.fromJson(json, IJsonDummy.class);
            System.out.print(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
