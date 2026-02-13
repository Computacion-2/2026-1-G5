package co.icesi.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class Initializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        XmlWebApplicationContext context = new XmlWebApplicationContext();

        context.setConfigLocation("classpath:applicationContext.xml");
        
        servletContext.addListener(new ContextLoaderListener(context));
    }
    
}
