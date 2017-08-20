package ru.otus.main;

import ru.otus.main.Lightson.Lightson;

public class Main {
    public static void main(String... args) throws Exception {
        String json;
        TestClass data = new TestClass();

        Lightson lightson = new Lightson();
        json = lightson.toJson(data);
        System.out.println(json);
    }
}
