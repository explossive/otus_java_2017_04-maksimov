package ru.otus.main.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Mapping {

    private String idFieldName;
    private List<Field> fieldBounds;
    private String tableName;

    /**
     *
     */
    public Mapping() {
        this.fieldBounds = new ArrayList<>();
    }

    /**
     * @param javaFieldName
     * @param dbColumnName
     */
    public void addFieldBound(String javaFieldName, String dbColumnName) {
        this.fieldBounds.add(new Field(javaFieldName, dbColumnName));
    }

    /**
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @param idFieldName
     */
    public void setIdFieldName(String idFieldName) {
        this.idFieldName = idFieldName;
    }

    /**
     * @return
     */
    public String getIdFieldName() {
        return idFieldName;
    }

    /**
     * @return
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return
     */
    public List<String> getDbFieldNames() {
        return fieldBounds.stream().map(Field::getDbColumnName).collect(Collectors.toList());
    }

    /**
     * @return
     */
    public List<Field> getFieldBounds() {
        return fieldBounds;
    }

}
