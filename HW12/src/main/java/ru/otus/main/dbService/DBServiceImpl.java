package ru.otus.main.dbService;

import org.apache.commons.lang3.SerializationUtils;
import ru.otus.main.base.DBService;
import ru.otus.main.base.dataSets.Address;
import ru.otus.main.base.dataSets.Phone;
import ru.otus.main.base.dataSets.User;
import ru.otus.main.cache.CacheEngine;
import ru.otus.main.dbService.dao.UserDataSetDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;


public class DBServiceImpl implements DBService {

    private CacheEngine<String, byte[]> cacheEngine;
    private final SessionFactory sessionFactory;

    public DBServiceImpl(CacheEngine<String, byte[]> cacheEngine) {
        this.cacheEngine = cacheEngine;
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Phone.class);
        configuration.addAnnotatedClass(Address.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        configuration.setProperty("hibernate.connection.username", "root"); //default login, allowed value - tully
        configuration.setProperty("hibernate.connection.password", "1234"); //default password, allowed value - tully
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
    }

    public String getLocalStatus() {
        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }

    public void save(User dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save(dataSet);
        }
    }

    public User read(long id) {
        String cacheKey = "uId-" + String.valueOf(id);
        byte[] cacheValue = cacheEngine.get(cacheKey);
        if (cacheValue != null) {
            return (User) SerializationUtils.deserialize(cacheValue);
        }
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            cacheEngine.put(cacheKey, SerializationUtils.serialize(dao.read(id)));
            return dao.read(id);
        });
    }

    public User readByName(String name) {
        String cacheKey = "uName-" + name;
        byte[] cacheValue = cacheEngine.get(cacheKey);
        if (cacheValue != null) {
            return (User) SerializationUtils.deserialize(cacheValue);
        }
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            User user = dao.readByName(name);
            cacheEngine.put(cacheKey, SerializationUtils.serialize(user));
            return dao.readByName(name);
        });
    }

    public List<User> readAll() {
        String cacheKey = "readAllUsers";
        byte[] cacheValue = cacheEngine.get(cacheKey);
        if (cacheValue != null) {
            return (List<User>) SerializationUtils.deserialize(cacheValue);
        }
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            List<User> users = dao.readAll();
            cacheEngine.put(cacheKey, SerializationUtils.serialize((Serializable) users));

            return dao.readAll();
        });
    }

    public void shutdown() {
        sessionFactory.close();
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
