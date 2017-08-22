package ru.otus.main.helper;


import ru.otus.main.orm.Field;
import ru.otus.main.orm.Mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QueryHelper {

    /**
     * @param entity
     * @param <T>
     * @return
     * @throws NoSuchFieldException
     */
    public static <T> String getUpdate(T entity) throws NoSuchFieldException {
        Mapping mapping = ReflectionHelper.getMapping(entity.getClass());
        List<String> dbNames = mapping.getDbFieldNames();

        Map<String, Object> valuesMap = new HashMap<>();
        for (Field field : mapping.getFieldBounds()) {
            valuesMap.put(field.getDbColumnName(), ReflectionHelper.getFieldValue(entity, field.getJavaFieldName()));
        }
        String columns = String.join(",", dbNames);
        String values = dbNames.stream().map(new Function<String, String>() {
            @Override
            public String apply(String dbName) {
                return getValue(valuesMap.get(dbName));
            }
        }).collect(Collectors.joining(","));
        String valuesWithNames = dbNames.stream().map(new Function<String, String>() {
            @Override
            public String apply(String dbName) {
                return String.format("%s=%s", dbName, getValue(valuesMap.get(dbName)));
            }
        }).collect(Collectors.joining(","));

        return String.format("INSERT INTO %s (%s) VALUES (%s)  ON DUPLICATE KEY UPDATE %s", mapping.getTableName(), columns, values, valuesWithNames);
    }

    /**
     * @param o
     * @return
     */
    private static String getValue(Object o) {
        if (o instanceof String || o instanceof Character) {
            return String.format("'%s'", o);
        } else {
            return o.toString();
        }
    }

    /**
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public static <T> String getSelect(Class<T> clazz, int id) {
        Mapping mapping = ReflectionHelper.getMapping(clazz);
        String fields = String.join(",", mapping.getDbFieldNames());
        return String.format("SELECT %s FROM %s WHERE %s = %s", fields, mapping.getTableName(), mapping.getIdFieldName(), id);
    }



}
