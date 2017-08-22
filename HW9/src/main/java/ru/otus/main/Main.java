package ru.otus.main;


import ru.otus.main.dao.UserDAOMySql;
import ru.otus.main.dao.UserDao;
import ru.otus.main.entity.User;
import ru.otus.main.helper.ConnectionHelper;
import ru.otus.main.orm.Orm;

public class Main {
    public static void main(String... args) throws Exception {
        Orm orm = new Orm(ConnectionHelper.getConnection());
        UserDao user = new UserDAOMySql(orm);

        user.insert(new User(1, "kek", 18));

        User user1 = user.findById(1);
        System.out.println(user1);
    }
}
