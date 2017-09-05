package ru.otus.main.dbService.messages;


import ru.otus.main.base.DBService;
import ru.otus.main.dbService.MsgToDB;
import ru.otus.main.messageSystem.Address;
import ru.otus.main.messageSystem.MessageSystem;


public class MsgGetUserId extends MsgToDB {
    private final MessageSystem messageSystem;
    private final String login;

    public MsgGetUserId(MessageSystem messageSystem, Address from, Address to, String login) {
        super(from, to);
        this.login = login;
        this.messageSystem = messageSystem;
    }

    @Override
    public void exec(DBService dbService) {
        long id = dbService.readByName(login).getId();
        messageSystem.sendMessage(new MsgGetUserIdAnswer(getTo(), getFrom(), login, id));
    }
}
