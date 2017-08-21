package ru.otus.main.helper;


import ru.otus.main.orm.Field;
import ru.otus.main.orm.Mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryHelper {

    public static <T> String getUpdate(T entity) throws NoSuchFieldException {
        Mapping mapping = ReflectionHelper.getMapping(entity.getClass());
        List<String> dbNames = mapping.getDbFieldNames();

        Map<String, Object> valuesMap = new HashMap<>();
        for (Field fieldBound : mapping.getFieldBounds()) {
            valuesMap.put(fieldBound.getDbName(), ReflectionHelper.getFieldValue(entity, fieldBound.getJavaName()));
        }
        String columns = String.join(",", dbNames);
        String values = dbNames.stream().map(dbName -> getValue(valuesMap.get(dbName))).collect(Collectors.joining(","));
        String valuesWithNames = dbNames.stream().map(dbName -> String.format("%s=%s", dbName, getValue(valuesMap.get(dbName)))).collect(Collectors.joining(","));

        return String.format("INSERT INTO %s (%s) VALUES (%s)  ON DUPLICATE KEY UPDATE %s", mapping.getTableName(), columns, values,  valuesWithNames);
    }

    private static String getValue(Object o) {
        if (o instanceof String || o instanceof Character) {
            return String.format("'%s'", o);
        } else {
            return o.toString();
        }
    }
}
