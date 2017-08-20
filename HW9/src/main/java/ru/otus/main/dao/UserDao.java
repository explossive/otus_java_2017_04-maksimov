package ru.otus.main.dao;


import ru.otus.main.dataSet.UserDataSet;
import ru.otus.main.entity.User;
import ru.otus.main.orm.Orm;


public class UserDao {
    private Orm orm;

    public UserDao(Orm orm) {
        this.orm = orm;
    }

    public void save(User user) {
        orm.save(user);
    }

}