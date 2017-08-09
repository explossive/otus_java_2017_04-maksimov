package ru.otus.main;

import com.google.gson.Gson;
import ru.otus.main.Lightson.Lightson;

public class Main {
    public static void main(String... args) throws Exception {

        String json;

        Gson gson = new Gson();
        json = gson.toJson(new PrimitiveClass());
        System.out.println(json);

        Lightson lightson = new Lightson();
        json = lightson.toJson(new PrimitiveClass());
        System.out.println(json);
    }
}
