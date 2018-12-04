package core.dynamicworksheet.util;

import com.google.gson.*;

import core.dynamicworksheet.type.ValidationCaseType;

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
                return core.dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseRequired.class;
            case MinLength:
                return core.dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseMinLength.class;
            case Upload:
                return core.dynamicworksheet.jsondummy.validation.validationcase.JsonDummyValidationCaseUpload.class;
        }
        return Object.class;
    }
}
