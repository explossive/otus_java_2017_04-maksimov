package ru.otus.main;

import ru.otus.main.base.dataSets.Address;
import ru.otus.main.base.dataSets.Phone;
import ru.otus.main.base.dataSets.User;
import ru.otus.main.cache.CacheEngineImpl;
import ru.otus.main.dbService.DBServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBServiceImpl dbService = new DBServiceImpl(new CacheEngineImpl<>(10, 0, 0, true));

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        dbService.save(new User("1111", new Address("111", 456657), new Phone("1111")));
        dbService.save(new User("2222", new Address("222", 456657), new Phone("2222")));

        User data = dbService.read(1);
        System.out.println(data);
        data = dbService.read(1);
        System.out.println(data);

        data = dbService.readByName("1111");
        System.out.println(data);
        data = dbService.readByName("1111");
        System.out.println(data);

        List<User> dataSets = dbService.readAll();
        dataSets.forEach(System.out::println);
        dataSets = dbService.readAll();
        dataSets.forEach(System.out::println);

        dbService.shutdown();
    }
}