package dynamicworksheet.util;

import com.google.gson.*;
import dynamicworksheet.jsondummy.*;
import dynamicworksheet.type.UIType;

import java.lang.reflect.Type;

/**
 * десериализатор для IJsonDummy
 */
public class ElementGsonAdapter implements JsonDeserializer {

    private static final String TYPE_KEY = "ui";

    @Override
    public Object deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        UIType type = context.deserialize(jsonObject.get(TYPE_KEY), UIType.class);
        return context.deserialize(jsonElement, getClassByType(type));
    }

    private Class getClassByType(UIType type) {
        switch (type) {
            case Input:
                return JsonDummyInput.class;
            case Date:
                return JsonDummyDate.class;
            case Text:
                return JsonDummyText.class;
            case Select:
                return JsonDummySelect.class;
            case CheckBox:
                return JsonDummyCheck.class;
            case FileUpload:
                return JsonDummyFile.class;
            case ImageView:
                return JsonDummyImage.class;
            case RadioGroup:
                return JsonDummyRadio.class;
            case Container:
                return JsonDummyContainer.class;
            case Wizard:
                return JsonDummyWizard.class;
        }
        return Object.class;
    }
}
