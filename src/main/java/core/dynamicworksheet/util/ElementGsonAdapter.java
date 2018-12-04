package core.dynamicworksheet.util;

import com.google.gson.*;

import core.dynamicworksheet.jsondummy.JsonDummyWizard;
import core.dynamicworksheet.type.UIType;

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
                return core.dynamicworksheet.jsondummy.JsonDummyInput.class;
            case Date:
                return core.dynamicworksheet.jsondummy.JsonDummyDate.class;
            case Text:
                return core.dynamicworksheet.jsondummy.JsonDummyText.class;
            case Select:
                return core.dynamicworksheet.jsondummy.JsonDummySelect.class;
            case CheckBox:
                return core.dynamicworksheet.jsondummy.JsonDummyCheck.class;
            case FileUpload:
                return core.dynamicworksheet.jsondummy.JsonDummyFile.class;
            case ImageView:
                return core.dynamicworksheet.jsondummy.JsonDummyImage.class;
            case RadioGroup:
                return core.dynamicworksheet.jsondummy.JsonDummyRadio.class;
            case Container:
                return core.dynamicworksheet.jsondummy.JsonDummyContainer.class;
            case Wizard:
                return JsonDummyWizard.class;
        }
        return Object.class;
    }
}
