package ru.otus.main.Lightson.strategy;

import ru.otus.main.Lightson.elements.JsonElement;
import ru.otus.main.Lightson.elements.JsonNull;


public class JsonNullStrategy implements JsonStrategy {
    @Override
    public JsonElement buildElement(Object value, String name) {
        return new JsonNull();
    }
}
