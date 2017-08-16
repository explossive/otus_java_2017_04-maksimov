package ru.otus.main.Lightson;


public class JsonBuilder {
    public JsonBuilder(Object obj) {
    }

    public JsonElement build(Object obj) {
        return new JsonArray();
    }
}
