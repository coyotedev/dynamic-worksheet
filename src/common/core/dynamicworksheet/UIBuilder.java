package core.dynamicworksheet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import core.dynamicworksheet.element.IElement;
import core.dynamicworksheet.jsondummy.IJsonDummy;
import core.dynamicworksheet.jsondummy.validation.validationcase.IJsonDummyValidationCase;
import core.dynamicworksheet.jsondummy.value.IJsonDummyValue;
import core.dynamicworksheet.util.ElementGsonAdapter;
import core.dynamicworksheet.util.ValidationCaseGsonAdapter;
import core.dynamicworksheet.util.ValueGsonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для парсинга джейсона в список ядерных элементов (как правило, в списке один элемент - визард)
 * Всё взаимодействие между элементами в рамках одного джейсона происходит под капотом, при реализации
 * UI на конкретной платформе реализовывать эту логику не нужно. Подробнее см. в комментариях для
 * {@link IElement}
 */
public class UIBuilder {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(IJsonDummy.class, new ElementGsonAdapter())
            .registerTypeAdapter(IJsonDummyValue.class, new ValueGsonAdapter())
            .registerTypeAdapter(IJsonDummyValidationCase.class, new ValidationCaseGsonAdapter())
            .create();

    /**
     * Парсит джейсон в список ядерных элементов
     * @param json джейсон с описанием элементов
     * @return список ядерных элементов, которые затем через адаптеры RUI отображаются на экране
     */
    public static List<IElement> parse(String json) {
        List<IElement> ret = new ArrayList<>();
        try {
            JsonArray js = new JsonParser().parse(json).getAsJsonArray();
            for (int i = 0; i < js.size(); ++i) {
                IJsonDummy root = GSON.fromJson(js.get(i).toString(), IJsonDummy.class);
                ret.add(root.getElement(null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
