package myapp;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ExampleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("myapp.Application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("myapp.Application stopped");
    }
}