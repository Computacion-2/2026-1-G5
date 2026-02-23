package co.icesi.servlets;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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
    private UsersView view;

    @Override
    public void init() throws ServletException {
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
        builder.append("<head>");
        builder.append("<link rel=\"stylesheet\" href=\"/auth/userList.css\">");
        builder.append("<script src=\"/auth/userList.js\"></script>");
        builder.append("</head>");
        builder.append("<body>");
        builder.append("<div id=\"successBox\" class=\"message\"> Usuario agregado exitosamente</div>");
        builder.append("<a href=\"/auth/login\" class=\"logout\">Cerrar sesión</a>");
        builder.append("<h1>Lista de usuarios</h1>");
        builder.append("<div class=\"table-container\">");
        builder.append(view.listUsers(users));
        builder.append("</div>");
        builder.append("<div class=\"form-container\">");
        builder.append(view.createUserForm());
        builder.append("</div>");
        builder.append("</body>");
        builder.append("</html>");

        resp.setContentType("text/html");
        resp.getWriter().println(builder.toString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("firstName");
        String roles = req.getParameter("roles");
        resp.setStatus(HttpServletResponse.SC_SEE_OTHER); // 303
        try {
            service.addUser(name, username, password, roles);
            resp.setHeader("Location", req.getContextPath() + "/users?success=true");
        } catch (Exception e) {
            resp.setHeader("Location", req.getContextPath() + "/users?success=false");
        }


    }

}
