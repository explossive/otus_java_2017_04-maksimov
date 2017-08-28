package ru.otus.main;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.main.base.DBService;
import ru.otus.main.base.dataSets.Address;
import ru.otus.main.base.dataSets.Phone;
import ru.otus.main.base.dataSets.User;
import ru.otus.main.cache.CacheEngineImpl;
import ru.otus.main.dbService.DBServiceImpl;
import ru.otus.main.servlet.AdminServlet;
import ru.otus.main.servlet.CacheServlet;
import ru.otus.main.servlet.LoginServlet;
import ru.otus.main.servlet.TimerServlet;

import java.util.List;


public class Main {

    private final static int PORT = 8080;
    private final static String PUBLIC_HTML = "HW12/public_html";

    public static void main(String[] args) throws Exception {
        DBService dbService = new DBServiceImpl(new CacheEngineImpl<>(10, 0, 0, true));
        dbServerInit(dbService);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new LoginServlet("anonymous", "anonymous")), "/login");
        context.addServlet(AdminServlet.class, "/admin");
        context.addServlet(TimerServlet.class, "/timer");
        context.addServlet(new ServletHolder(new CacheServlet(dbService)), "/cache");
        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }

    private static void dbServerInit(DBService dbService) {
        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);
        dbService.save(new User("1111", new Address("111", 456657), new Phone("1111")));
        dbService.save(new User("2222", new Address("222", 456657), new Phone("2222")));

        User data = dbService.read(1);
        data = dbService.read(1);

        data = dbService.readByName("1111");
        data = dbService.readByName("1111");
        data = dbService.readByName("1111");
    }
}