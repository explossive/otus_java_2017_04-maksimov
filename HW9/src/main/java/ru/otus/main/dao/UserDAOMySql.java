package ru.otus.main.dao;

import ru.otus.main.entity.User;
import ru.otus.main.orm.Orm;


public class UserDAOMySql implements UserDao {
    private Orm orm;

    public UserDAOMySql(Orm orm) {
        this.orm = orm;
    }

    public void insert(User user) {
        orm.save(user);
    }

    public User findById(int id) {
        return new User();
    }

    public void delete(int id) {

    }
}