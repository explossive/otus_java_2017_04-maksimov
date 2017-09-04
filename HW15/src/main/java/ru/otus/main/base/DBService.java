package ru.otus.main.base;

import ru.otus.main.base.dataSets.User;

import java.util.List;

public interface DBService {
    String getLocalStatus();

    void save(User dataSet);

    User read(long id);

    User readByName(String name);

    List<User> readAll();

    void shutdown();

    int[] getCacheStats();
}
