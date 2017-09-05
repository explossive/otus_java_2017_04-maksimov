package ru.otus.main.front;

import ru.otus.main.dbService.FrontendService;
import ru.otus.main.dbService.MessageSystemContext;
import ru.otus.main.base.dataSets.User;
import ru.otus.main.dbService.messages.*;
import ru.otus.main.messageSystem.Address;
import ru.otus.main.messageSystem.Addressee;
import ru.otus.main.messageSystem.Message;


public class FrontendServiceImpl implements FrontendService, Addressee {
    private final Address address;
    private final MessageSystemContext context;


    public FrontendServiceImpl(MessageSystemContext context, Address address) {
        this.context = context;
        this.address = address;
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    public void handleRequestReadUser(long id) {
        Message message = new MsgGetUser(context.getMessageSystem(), getAddress(), context.getDbAddress(), id);
        context.getMessageSystem().sendMessage(message);
    }

    public void handleRequestReadUserByName(String login) {
        Message message = new MsgGetUserId(context.getMessageSystem(), getAddress(), context.getDbAddress(), login);
        context.getMessageSystem().sendMessage(message);
    }

    public void handleRequestSaveUser(User user) {
        Message message = new MsgSaveUser(context.getMessageSystem(), getAddress(), context.getDbAddress(), user);
        context.getMessageSystem().sendMessage(message);
    }

    public void handleRequestReadAllUsers() {
        Message message = new MsgGetAllUsers(context.getMessageSystem(), getAddress(), context.getDbAddress());
        context.getMessageSystem().sendMessage(message);
    }

}