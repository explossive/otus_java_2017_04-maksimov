package ru.otus.main.dbService.messages;

import ru.otus.main.base.DBService;
import ru.otus.main.dbService.MsgToDB;
import ru.otus.main.base.dataSets.User;
import ru.otus.main.messageSystem.Address;
import ru.otus.main.messageSystem.MessageSystem;

public class MsgGetUser extends MsgToDB {
    private final MessageSystem messageSystem;
    private final long id;

    public MsgGetUser(MessageSystem messageSystem, Address from, Address to, long id) {
        super(from, to);
        this.id = id;
        this.messageSystem = messageSystem;
    }

    @Override
    public void exec(DBService dbService) {
        User userDataSet = dbService.read(id);
        messageSystem.sendMessage(new MsgGetUserAnswer(getTo(), getFrom(), userDataSet));
    }
}
