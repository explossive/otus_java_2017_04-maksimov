package ru.otus.main;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.main.base.DBService;
import ru.otus.main.base.dataSets.Address;
import ru.otus.main.base.dataSets.Phone;
import ru.otus.main.base.dataSets.User;
import ru.otus.main.dbService.DBServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class HibernateTest {

    private static DBServiceImpl dbService;

    @BeforeClass
    public static void setupHibernate() {
        dbService = new DBServiceImpl();
    }

    @Test
    public void insertUserTest() {
        Address address = new Address("111", 456657);
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone("45748537"));
        phones.add(new Phone("34523523"));
        User user = new User("Test", address, phones);
        DBService dbService = new DBServiceImpl();
        dbService.save(user);
        User readUser = dbService.read(1);
        Assert.assertEquals("User is not found", readUser.getId(), user.getId());
        Assert.assertEquals("User is not found", readUser.getName(), user.getName());
    }

    @AfterClass
    public static void closeHibernate() {
        dbService.shutdown();
    }
}
