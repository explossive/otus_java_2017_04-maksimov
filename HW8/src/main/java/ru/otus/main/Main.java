package ru.otus.main;

import com.google.gson.Gson;
import ru.otus.main.Lightson.Lightson;

public class Main {
    public static void main(String... args) throws Exception {

        String json;
        int[] data = {1, 25, 56};
        Gson gson = new Gson();
        json = gson.toJson(data);
        System.out.println(json);

        Lightson lightson = new Lightson();
        json = lightson.toJson(data);
        System.out.println(json);
    }
}
