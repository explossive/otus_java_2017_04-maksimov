package ru.otus.main.dao;

import ru.otus.main.entity.User;
import ru.otus.main.orm.Orm;

/**
 * User DAO MySql
 */
public class UserDAOMySql implements UserDao {

    private Orm orm;

    /**
     * @param orm
     */
    public UserDAOMySql(Orm orm) {
        this.orm = orm;
    }

    /**
     * @param user
     */
    public void insert(User user) {
        orm.save(user);
    }

    /**
     * @param id
     * @return
     */
    public User findById(int id) {
        return orm.select(User.class, id);
    }

}