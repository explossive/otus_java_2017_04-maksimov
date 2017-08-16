package ru.otus.main.Lightson;



import ru.otus.main.PrimitiveClass;

import java.lang.reflect.Field;

public class Lightson implements Json {

    /**
     * @param obj
     * @return
     */
    @Override
    public String toJson(Object obj) {
        JsonBuilder builder = new JsonBuilder(obj);
        return builder.build(obj).getJson();
    }


    /**
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T fromJson(String json, Class<T> clazz) {
        return null;
    }





}
