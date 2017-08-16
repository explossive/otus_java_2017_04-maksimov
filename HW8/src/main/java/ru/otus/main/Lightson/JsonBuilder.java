package ru.otus.main.Lightson;


import ru.otus.main.Lightson.elements.JsonArray;
import ru.otus.main.Lightson.elements.JsonElement;

public class JsonBuilder {
    public JsonBuilder(Object obj) {
    }

    public JsonElement build(Object obj) {
        return new JsonArray();
    }
}
