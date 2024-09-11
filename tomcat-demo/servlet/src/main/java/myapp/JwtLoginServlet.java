package myapp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet("/api/login")
public class JwtLoginServlet extends HttpServlet {

    private static final String SECRET = ConfigUtil.getProperty("jwt.secret");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 简单的用户验证逻辑
        if ("admin".equals(username) && "password".equals(password)) {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create().withIssuer("auth0").withClaim("username", username).withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hour expiration
                    .sign(algorithm);

            Cookie cookie = new Cookie("token", token);
            // cookie.setHttpOnly(true); // 防止客户端脚本访问
            // cookie.setSecure(true); // 仅在 HTTPS 下传输
            cookie.setPath("/"); // 设置为在整个应用内有效
            cookie.setMaxAge(3600); // 1 hour expiration
            response.addCookie(cookie);
            response.getWriter().println("Login successful! JWT is in the cookie.");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Invalid username or password!");
        }
    }
}