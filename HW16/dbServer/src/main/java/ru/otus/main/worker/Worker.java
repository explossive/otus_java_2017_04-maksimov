package ru.otus.main.worker;

import ru.otus.main.base.DBService;
import ru.otus.main.base.dataSets.Address;
import ru.otus.main.base.dataSets.Phone;
import ru.otus.main.base.dataSets.User;

import java.util.Timer;
import java.util.TimerTask;

public class Worker {

    private DBService dbService;

    public Worker(DBService dbService) {
        this.dbService = dbService;
    }

    public void run() {

        String status = dbService.getLocalStatus();

        dbService.save(new User("1111", new Address("111", 456657), new Phone("1111")));
        dbService.save(new User("2222", new Address("222", 456657), new Phone("2222")));

        User data = dbService.read(1);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                dbService.read(1);
                dbService.readByName("1111");
                dbService.readByName("1111");
                dbService.readByName("1111");
            }
        }, 1000, 1000);
    }
}