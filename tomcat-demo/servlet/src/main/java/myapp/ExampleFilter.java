package myapp;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@WebFilter(urlPatterns = {"/example"})
public class ExampleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ExampleFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Request received at ExampleFilter");

        // 继续处理请求
        chain.doFilter(request, response);

        System.out.println("Response going through ExampleFilter");
    }

    @Override
    public void destroy() {
        System.out.println("ExampleFilter destroyed");
    }
}