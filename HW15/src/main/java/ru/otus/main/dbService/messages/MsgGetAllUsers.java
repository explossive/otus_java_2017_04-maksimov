package ru.otus.main.dbService.messages;

import ru.otus.main.base.DBService;
import ru.otus.main.dbService.MsgToDB;
import ru.otus.main.messageSystem.MessageSystem;
import ru.otus.main.base.dataSets.User;

import ru.otus.main.messageSystem.Address;


import java.util.List;

public class MsgGetAllUsers extends MsgToDB {
    private final MessageSystem messageSystem;

    public MsgGetAllUsers(MessageSystem messageSystem, Address from, Address to) {
        super(from, to);
        this.messageSystem = messageSystem;
    }

    @Override
    public void exec(DBService dbService) {
        List<User> userList = dbService.readAll();
        messageSystem.sendMessage(new MsgGetAllUsersAnswer(getTo(), getFrom(), userList));
    }
}
