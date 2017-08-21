package ru.otus.main.orm;

public class Field {
    private String javaName;
    private String dbName;

    public Field(String javaName, String dbName) {
        this.javaName = javaName;
        this.dbName = dbName;
    }

    public String getJavaName() {
        return javaName;
    }

    public String getDbName() {
        return dbName;
    }

}