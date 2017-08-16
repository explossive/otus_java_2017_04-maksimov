package ru.otus.main;

import com.google.gson.Gson;
import ru.otus.main.Lightson.Lightson;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) throws Exception {

        String json;
        List<Integer> list = new ArrayList<>();
        list.add(11111);
        list.add(22222);
        list.add(33333);
        list.add(44444);
        list.add(55555);
        Gson gson = new Gson();
        json = gson.toJson(list);
        System.out.println(json);

        Lightson lightson = new Lightson();
        json = lightson.toJson(list);
        System.out.println(json);
    }
}
