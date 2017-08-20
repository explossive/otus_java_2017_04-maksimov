package ru.otus.main.orm;


import java.sql.Connection;

public class Orm {

    private Connection connection;

    public Orm(Connection connection) {
        this.connection = connection;
    }

    public <T> boolean save(T object){
        return false;
    }
}
