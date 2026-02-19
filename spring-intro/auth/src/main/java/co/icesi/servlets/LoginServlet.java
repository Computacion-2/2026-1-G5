package co.icesi.servlets;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.icesi.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    private UserService service;
    
    @Override
    public void init() throws ServletException {
        ApplicationContext context = 
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        service = context.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean r = service.login("username", "password");

        resp.getWriter().println("Auth: "+r);
        resp.getWriter().println("Url: "+service.getUrl());
    }
}
