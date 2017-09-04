package ru.otus.main.front;

import ru.otus.main.dbService.FrontendService;
import ru.otus.main.messageSystem.Address;
import ru.otus.main.messageSystem.Addressee;
import ru.otus.main.messageSystem.Message;



public abstract class MsgToFrontend extends Message {


    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
            exec((FrontendService) addressee);

    }

    public abstract void exec(FrontendService frontendService);
}