package myapp.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import myapp.ConfigUtil;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebFilter("/protected/*")
public class JwtFilter implements Filter {
    private static final String SECRET = ConfigUtil.getProperty("jwt.secret");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        System.out.println("JwtFilter.doFilter");
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            String token = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals("token"))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
            if (token != null) {
                try {
                    Algorithm algorithm = Algorithm.HMAC256(SECRET);
                    JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
                    verifier.verify(token);
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpResponse.getWriter().println("Invalid token!");
                }
            } else {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().println("Token not found!");
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().println("Token not found!");
        }
    }
}