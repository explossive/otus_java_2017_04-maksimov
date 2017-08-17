package ru.otus.main.Lightson.strategy;


import ru.otus.main.Lightson.JsonBuilder;
import ru.otus.main.Lightson.elements.JsonElement;
import ru.otus.main.Lightson.elements.JsonObject;
import ru.otus.main.Lightson.helper.JsonHelper;

import java.lang.reflect.Array;

public class JsonArrayStrategy implements JsonStrategy {
    @Override
    public JsonElement buildElement(Object value, String name) {

        JsonObject jsonObject = new JsonObject();

        int length = Array.getLength(value);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(value, i);
            JsonBuilder jsonElementBuilder = new JsonBuilder(JsonHelper.getStrategy(element));
            jsonObject.add(jsonElementBuilder.build(element));
        }
        return jsonObject;
    }
}
