package ru.otus.main;


import ru.otus.main.dao.UserDao;
import ru.otus.main.dao.UserDaoImpl;
import ru.otus.main.entity.User;
import ru.otus.main.helper.ConnectionHelper;
import ru.otus.main.orm.Orm;


public class Main {
    public static void main(String... args) throws Exception {
        Orm orm = new Orm(ConnectionHelper.getConnection());
        UserDao user = new UserDaoImpl(orm);
        user.save(new User(1, "kek", 2));


        UserDao user1 = new UserDaoImpl(orm);

    }

}
