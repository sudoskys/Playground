package myapp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebFilter("/protected/*") // 拦截需要保护的 URL 模式
public class JwtCookieFilter implements Filter {

    private static final String SECRET = ConfigUtil.getProperty("jwt.secret");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = null;
        if (httpRequest.getCookies() != null) {
            token = Arrays.stream(httpRequest.getCookies()).filter(cookie -> "token".equals(cookie.getName())).findFirst().map(Cookie::getValue).orElse(null);
        }

        if (token != null) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
                DecodedJWT jwt = verifier.verify(token);
                String username = jwt.getClaim("username").asString();
                request.setAttribute("username", username);
                chain.doFilter(request, response); // 验证通过，继续请求链
            } catch (JWTVerificationException exception) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().println("Invalid token!");
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().println("Token not found!");
        }
    }

    @Override
    public void destroy() {
    }
}