package ru.otus.main.orm;


import ru.otus.main.helper.QueryHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
