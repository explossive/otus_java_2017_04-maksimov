package ru.otus.main.orm;

public class Field {
    private String javaFieldName;
    private String dbColumnName;

    public Field(String javaFieldName, String dbColumnName) {
        this.javaFieldName = javaFieldName;
        this.dbColumnName = dbColumnName;
    }

    /**
     * @return
     */
    public String getJavaFieldName() {
        return javaFieldName;
    }

    /**
     * @return
     */
    public String getDbColumnName() {
        return dbColumnName;
    }

}