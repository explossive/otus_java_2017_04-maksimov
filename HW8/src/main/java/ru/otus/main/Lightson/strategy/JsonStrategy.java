package ru.otus.main.Lightson.strategy;

import ru.otus.main.Lightson.elements.JsonElement;

public interface JsonStrategy {
    JsonElement buildElement(Object value, String name);
}
