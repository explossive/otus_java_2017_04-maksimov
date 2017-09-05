package ru.otus.main.dbService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.main.base.DBService;
import ru.otus.main.messageSystem.Address;
import ru.otus.main.messageSystem.Addressee;
import ru.otus.main.messageSystem.Message;

import java.time.LocalDateTime;

public abstract class MsgToDB extends Message {
    private final Logger LOGGER = LoggerFactory.getLogger(MsgToDB.class);

    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            LOGGER.info(LocalDateTime.now() + ":    Executing request for DBService addressee");
            exec((DBService) addressee);
        } else {
            LOGGER.error(LocalDateTime.now() + ":    Wrong addressee! This addressee works only with DBService " +
                    "requests!");
        }
    }

    public abstract void exec(DBService dbService);
}
