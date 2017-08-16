package ru.otus.main.Lightson;

import ru.otus.main.Lightson.helper.JsonHelper;

public class Lightson {

    /**
     * @param element
     * @return
     */
    public String toJson(Object element) {
        return new JsonBuilder(JsonHelper.getStrategy(element)).build(element).getJson();
    }
}
