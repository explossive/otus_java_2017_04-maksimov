package ru.otus.main.Lightson.strategy;

import ru.otus.main.Lightson.elements.JsonElement;
import ru.otus.main.Lightson.elements.JsonPrimitive;


public class JsonPrimitiveStrategy implements JsonStrategy {
    @Override
    public JsonElement buildElement(Object value, String name) {
        return new JsonPrimitive(name, value);
    }
}
