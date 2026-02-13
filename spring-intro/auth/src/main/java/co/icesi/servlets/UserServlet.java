package co.icesi.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.Provider.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.google.gson.Gson;

import co.icesi.model.User;
import co.icesi.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServlet extends HttpServlet{

    private UserService service;
    private Gson encoder;

    @Override
    public void init() throws ServletException {
        encoder = new Gson();
        ApplicationContext context = 
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        service = context.getBean(UserService.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<User> users = service.getUsers();

        String data = encoder.toJson(users);

        resp.getWriter().println(data);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String body = "";
        boolean end = false;
        while (!end) {
            String current = reader.readLine();
            if(current == null){
                end = true;
            }else{
                body += current;
            }
        }

        Map<String,String> data = encoder.fromJson(body, HashMap.class);
        System.out.println(data);
    }
}
