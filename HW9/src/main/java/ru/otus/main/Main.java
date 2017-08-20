package ru.otus.main;


import ru.otus.main.dao.UserDao;
import ru.otus.main.entity.User;
import ru.otus.main.helper.ConnectionHelper;
import ru.otus.main.orm.Orm;


public class Main {
    public static void main(String... args) throws Exception {
        User userEntity = new User(1, "kek", 2);
        Orm orm = new Orm(ConnectionHelper.getConnection());
        UserDao user = new UserDao(orm);
        user.save(userEntity);
    }

}
