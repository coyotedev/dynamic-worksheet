package dynamicworksheet.util;

import com.google.gson.*;
import dynamicworksheet.jsondummy.operations.JsonDummyOperationBinary;
import dynamicworksheet.jsondummy.operations.JsonDummyOperationMany;
import dynamicworksheet.jsondummy.operations.JsonDummyOperationOne;
import dynamicworksheet.jsondummy.value.JsonDummyValueConst;
import dynamicworksheet.jsondummy.value.JsonDummyValueModel;
import dynamicworksheet.jsondummy.value.JsonDummyValueRef;
import dynamicworksheet.jsondummy.value.JsonDummyValuei18n;
import dynamicworksheet.types.ValueType;

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
                return JsonDummyValueConst.class;
            case I18N:
                return JsonDummyValuei18n.class;
            case Ref:
                return JsonDummyValueRef.class;
            case Model:
                return JsonDummyValueModel.class;
            case And:
            case Or:
                return JsonDummyOperationMany.class;
            case Not:
                return JsonDummyOperationOne.class;
            case Equal:
                return JsonDummyOperationBinary.class;
        }
        return Object.class;
    }
}
