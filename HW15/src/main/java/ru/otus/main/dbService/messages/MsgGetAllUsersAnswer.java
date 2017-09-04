package ru.otus.main.dbService.messages;


import ru.otus.main.base.dataSets.User;
import ru.otus.main.dbService.FrontendService;
import ru.otus.main.front.MsgToFrontend;
import ru.otus.main.messageSystem.Address;


import java.util.List;

public class MsgGetAllUsersAnswer extends MsgToFrontend {
    private final List<User> userList;

    public MsgGetAllUsersAnswer(Address from, Address to, List<User> userList) {
        super(from, to);
        this.userList = userList;
    }

    @Override
    public void exec(FrontendService frontendService) {
    }
}
