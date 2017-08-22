package ru.otus.main.orm;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Mapping {


    private String idFieldName;
    private List<Field> fieldBounds;
    private String tableName;

    public Mapping() {
        this.fieldBounds = new ArrayList<>();
    }

    public void addFieldBound(String javaFieldName, String dbColumnName) {
        this.fieldBounds.add(new Field(javaFieldName, dbColumnName));
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setIdFieldName(String idFieldName) {
        this.idFieldName = idFieldName;
    }

    public String getIdFieldName() {
        return idFieldName;
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getDbFieldNames() {
        return fieldBounds.stream().map(Field::getDbName).collect(Collectors.toList());
    }

    public List<Field> getFieldBounds() {
        return fieldBounds;
    }

    public int getColumnsCount() {
        return fieldBounds.size();
    }
}
