import java.io.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
         message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


   ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // Obtener el bean definido en Spring
        HolaMundo hola = (HolaMundo) context.getBean("HolaMundo");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h1>" + hola.getMensaje() + "</h1>");
        hola.setMensaje("New message");
        OtherBean bean = (OtherBean) context.getBean("OtherBean");
        out.println("<h1>" + bean.getHm().getMensaje() + "</h1>");

         out.println("</body></html>");
    }

    public void destroy() {
    }
}