package ru.otus.main.dbService;

import ru.otus.main.base.dataSets.User;

import java.util.Map;

public interface FrontendService {
    void init();

    void handleRequestReadUserByName(String login);

    void handleRequestSaveUser(User userDataSet);

    void handleRequestReadUser(long id);

    void handleRequestReadAllUsers();
}

