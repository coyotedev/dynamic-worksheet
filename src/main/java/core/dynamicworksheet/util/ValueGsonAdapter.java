package core.dynamicworksheet.util;

import com.google.gson.*;

import core.dynamicworksheet.type.ValueType;

import java.lang.reflect.Type;

public class ValueGsonAdapter implements JsonDeserializer {

    private static final String TYPE_KEY = "type";

    @Override
    public Object deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        ValueType type = context.deserialize(jsonObject.get(TYPE_KEY), ValueType.class);
        return context.deserialize(jsonElement, getClassByType(type));
    }

    private Class getClassByType(ValueType type) {
        switch (type) {
            case Const:
                return core.dynamicworksheet.jsondummy.value.JsonDummyValueConst.class;
            case I18N:
                return core.dynamicworksheet.jsondummy.value.JsonDummyValuei18n.class;
            case Ref:
                return core.dynamicworksheet.jsondummy.value.JsonDummyValueRef.class;
            case Model:
                return core.dynamicworksheet.jsondummy.value.JsonDummyValueModel.class;
            case And:
            case Or:
                return core.dynamicworksheet.jsondummy.value.operation.JsonDummyOperationMany.class;
            case Not:
                return core.dynamicworksheet.jsondummy.value.operation.JsonDummyOperationOne.class;
            case Equal:
                return core.dynamicworksheet.jsondummy.value.operation.JsonDummyOperationBinary.class;
        }
        return Object.class;
    }
}
