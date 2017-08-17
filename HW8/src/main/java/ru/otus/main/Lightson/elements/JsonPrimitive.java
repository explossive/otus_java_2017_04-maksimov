package ru.otus.main.Lightson.elements;

public class JsonPrimitive implements JsonElement {

    private String name;
    private Object value;

    public JsonPrimitive() {
    }

    public JsonPrimitive(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getJson() {
        String jsonValue = (value instanceof String) ? String.format("\"%s\"", value) : value.toString();
        if (name == null || name.isEmpty()) {
            return jsonValue;
        } else {
            return String.format( "\"%s\":%s", name, jsonValue);
        }
    }
}
