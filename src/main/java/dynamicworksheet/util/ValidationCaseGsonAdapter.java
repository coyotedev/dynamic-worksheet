package dynamicworksheet.util;

import com.google.gson.*;
import dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseMinLength;
import dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseRequired;
import dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseUpload;
import dynamicworksheet.types.ValidationCaseType;

import java.lang.reflect.Type;

public class ValidationCaseGsonAdapter implements JsonDeserializer {
    private static final String TYPE_KEY = "type";

    @Override
    public Object deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        ValidationCaseType type = context.deserialize(jsonObject.get(TYPE_KEY), ValidationCaseType.class);
        return context.deserialize(jsonElement, getClassByType(type));
    }

    private Class getClassByType(ValidationCaseType type) {
        switch (type) {
            case Required:
                return JsonDummyValidationCaseRequired.class;
            case MinLength:
                return JsonDummyValidationCaseMinLength.class;
            case Upload:
                return JsonDummyValidationCaseUpload.class;
        }
        return Object.class;
    }
}
