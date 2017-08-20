package ru.otus.main.Lightson.elements;


public class JsonField {
    private String name;
    private Object value;

    public JsonField(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
