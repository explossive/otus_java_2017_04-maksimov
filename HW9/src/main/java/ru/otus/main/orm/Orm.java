package ru.otus.main.orm;


import ru.otus.main.helper.QueryHelper;
import ru.otus.main.helper.ReflectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class Orm {

    private Connection connection;

    /**
     * @param connection
     */
    public Orm(Connection connection) {
        this.connection = connection;
    }

    /**
     * @param object
     * @param <T>
     * @return
     */
    public <T> boolean save(T object) {
        try (Statement statement = connection.createStatement()) {
            int updatedRows = statement.executeUpdate(QueryHelper.getUpdate(object), Statement.RETURN_GENERATED_KEYS);
            if (updatedRows == 1) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            return false;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        return false;
    }


    /**
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public <T> T select(Class<T> clazz, int id) {
        return (T) exec(QueryHelper.getSelect(clazz, id), resultSet -> {
            AtomicReference<T> instance = new AtomicReference<>(ReflectionHelper.getNewInstance(clazz));
            if (instance.get() == null) {
                return null;
            }
            try {
                if (resultSet.next()) {
                    Mapping entityMapping = ReflectionHelper.getMapping(clazz);
                    for (Field fieldBound : entityMapping.getFieldBounds()) {
                        ReflectionHelper.setFieldValue(instance.get(), fieldBound.getJavaFieldName(), resultSet.getObject(fieldBound.getDbColumnName()));
                    }
                }
                return instance.get();
            } catch (SQLException e) {
                return null;
            }
        });

    }


    /**
     * @param query
     * @param function
     * @param <T>
     * @return
     */
    private  <T> T exec(String query, Function<ResultSet, T> function) {
        try (Statement statement = connection.createStatement()){
            statement.execute(query);
            return function.apply(statement.getResultSet());
        } catch (SQLException e) {
            return null;
        }
    }
}
