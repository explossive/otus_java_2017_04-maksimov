package ru.otus.main.servlet;

import ru.otus.main.base.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CacheServlet extends HttpServlet {
    private static final String ACCESS_DENIED_PAGE_TEMPLATE = "403.html";
    private static final String CACHE_PAGE_TEMPLATE = "cache.html";

    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";

    private DBService dbService;

    public CacheServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createCacheVariablesMap();
        String login = (String) request.getSession().getAttribute(LoginServlet.LOGIN_PARAMETER_NAME);
        String password = (String) request.getSession().getAttribute(LoginServlet.PASSWORD_PARAMETER_NAME);
        login = (null == login) ? "" : login;
        password = (null == password) ? "" : password;
        if (!login.equals(LOGIN) || !password.equals(PASSWORD)) {
            response.getWriter().println(TemplateProcessor.instance().getPage(ACCESS_DENIED_PAGE_TEMPLATE, null));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
        } else {
            String page = TemplateProcessor.instance().getPage(CACHE_PAGE_TEMPLATE, pageVariables);
            response.getWriter().println(page);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private Map<String, Object> createCacheVariablesMap() {
        Map<String, Object> pageVariables = new HashMap<>();
        int[] cacheStats = dbService.getCacheStats();
        pageVariables.put("hit", cacheStats[0]);
        pageVariables.put("miss", cacheStats[1]);
        pageVariables.put("element", cacheStats[2]);
        return pageVariables;
    }

}
