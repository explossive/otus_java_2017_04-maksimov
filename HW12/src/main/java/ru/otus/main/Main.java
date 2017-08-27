package ru.otus.main;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.main.base.DBService;
import ru.otus.main.cache.CacheEngineImpl;
import ru.otus.main.dbService.DBServiceImpl;
import ru.otus.main.servlet.AdminServlet;
import ru.otus.main.servlet.LoginServlet;
import ru.otus.main.servlet.TimerServlet;


public class Main {

    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {
        DBService dbService = new DBServiceImpl(new CacheEngineImpl<>(10, 0, 0, true));
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new LoginServlet("anonymous")), "/login");
        context.addServlet(AdminServlet.class, "/admin");
        context.addServlet(TimerServlet.class, "/timer");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}