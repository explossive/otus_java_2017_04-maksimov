package ru.otus.main.dao;

import ru.otus.main.entity.User;
import ru.otus.main.orm.Orm;


public class UserDaoImpl implements UserDao {
    private Orm orm;

    public UserDaoImpl(Orm orm) {
        this.orm = orm;
    }

    public void save(User user) {
        orm.save(user);
    }

    public User findById(int id) {
        return new User();
    }

    public void delete(int id) {

    }
}