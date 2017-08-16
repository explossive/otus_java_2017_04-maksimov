package ru.otus.main.Lightson.strategy;

import ru.otus.main.Lightson.elements.JsonElement;
import ru.otus.main.Lightson.elements.JsonObject;

import java.util.Collection;


public class JsonCollectionStrategy implements JsonStrategy {

    @SuppressWarnings("unchecked")
    @Override
    public JsonElement buildElement(Object value, String name) {

        Collection<Object> collection = (Collection<Object>) value;


        return null;
    }
}
