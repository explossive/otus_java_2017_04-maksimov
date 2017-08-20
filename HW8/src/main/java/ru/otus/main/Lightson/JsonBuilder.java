package ru.otus.main.Lightson;

import ru.otus.main.Lightson.elements.JsonElement;
import ru.otus.main.Lightson.strategy.JsonStrategy;

public class JsonBuilder {

    private JsonStrategy jsonStrategy;
    private String name = null;

    public JsonBuilder(JsonStrategy jsonStrategy) {
        this.jsonStrategy = jsonStrategy;
    }

    public String getName() {
        return name;
    }
    public JsonBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public JsonElement build(Object object) {
        JsonElement element = jsonStrategy.buildElement(object, name);
        return element;
    }
}
