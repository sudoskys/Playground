package myapp;

import java.io.*;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "HelloWorld", urlPatterns = "/hello", initParams = {@WebInitParam(name = "message", value = "Hello World!")})
public class HelloWorld extends HttpServlet {
    private String message;
    private String appName;
    private String appVersion;

    @Override
    public void init() throws ServletException {
        super.init();
        // 初始化参数
        message = getInitParameter("message");
        // 从环境变量获取数据
        String envVariable = System.getenv("MY_ENV_VARIABLE");
        System.out.println("Environment Variable: " + envVariable);

        // 从配置文件 /resources/config.properties 获取数据
        try (InputStream input = getServletContext().getResourceAsStream("/WEB-INF/classes/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            appName = prop.getProperty("app.name");
            appVersion = prop.getProperty("app.version");

        } catch (IOException ex) {
            throw new ServletException("Could not read config file", ex);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Welcome to " + appName + "</h2>");
        response.getWriter().println("<p>Version: " + appVersion + "</p>");
        response.getWriter().println("<p>Environment Variable: " + System.getenv("MY_ENV_VARIABLE") + "</p>");
        response.getWriter().println("<p>" + message + "</p>");
        response.getWriter().println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置请求和响应的字符编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取表单参数
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        // 响应处理结果
        response.getWriter().println("<html><body>");

        response.getWriter().println("<h2>Form Submitted Successfully!</h2>");
        response.getWriter().println("<p>Username: " + username + "</p>");
        response.getWriter().println("<p>Email: " + email + "</p>");
        response.getWriter().println("<p>" + message + "</p>");
        response.getWriter().println("</body></html>");
    }

    @Override
    public void destroy() {
        // 作用：在 Servlet 生命周期结束时调用，比如释放资源等。
        // Servlet 生命周期什么时候结束？当应用停止时，或者 Servlet 容器关闭时。
        super.destroy();
    }

}