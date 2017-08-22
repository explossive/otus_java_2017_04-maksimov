package ru.otus.main.dao;


import ru.otus.main.entity.User;

public interface UserDao {

    public void insert(User user);

    public User findById(int id);

    public void delete(int id);
}
