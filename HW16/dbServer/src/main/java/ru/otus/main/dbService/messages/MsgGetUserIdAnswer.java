package ru.otus.main.dbService.messages;

import ru.otus.main.dbService.FrontendService;
import ru.otus.main.front.MsgToFrontend;
import ru.otus.main.messageSystem.Address;

public class MsgGetUserIdAnswer extends MsgToFrontend {
    private final String name;
    private final long id;

    public MsgGetUserIdAnswer(Address from, Address to, String name, long id) {
        super(from, to);
        this.name = name;
        this.id = id;
    }

    @Override
    public void exec(FrontendService frontendService) {
    }
}
