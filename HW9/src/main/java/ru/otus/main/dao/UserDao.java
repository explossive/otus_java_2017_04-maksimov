package ru.otus.main.dao;


import ru.otus.main.entity.User;

public interface UserDao {

    /**
     * @param user
     */
    public void insert(User user);

    /**
     * @param id
     * @return
     */
    public User findById(int id);

}
