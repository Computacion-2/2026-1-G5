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
import co.icesi.views.UsersView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServlet extends HttpServlet{

    private UserService service;
    private Gson encoder;
    private UsersView view;

    @Override
    public void init() throws ServletException {
        encoder = new Gson();
        ApplicationContext context = 
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        service = context.getBean(UserService.class);
        view = new UsersView();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = service.getUsers();
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");

        builder.append("<body>");
        builder.append(view.listUsers(users));
        builder.append("</body>");
        builder.append("</html>");

        resp.setContentType("text/html");
        resp.getWriter().println(builder.toString());

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

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doOptions(req, resp);
    }
}
