package ru.otus.main.dbService.messages;

import ru.otus.main.base.DBService;
import ru.otus.main.base.dataSets.User;
import ru.otus.main.dbService.MsgToDB;
import ru.otus.main.messageSystem.Address;
import ru.otus.main.messageSystem.MessageSystem;

public class MsgSaveUser extends MsgToDB {
    private final MessageSystem messageSystem;
    private final User user;

    public MsgSaveUser(MessageSystem messageSystem, Address from, Address to, User userDataSet) {
        super(from, to);
        this.user = userDataSet;
        this.messageSystem = messageSystem;
    }

    @Override
    public void exec(DBService dbService) {
        dbService.save(user);
        messageSystem.sendMessage(new MsgSaveUserAnswer(getTo(), getFrom(), user));
    }
}
