package ru.sevastopall.http.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sevastopall.http.dto.UserDto;
import ru.sevastopall.http.util.UrlPath;

import java.io.IOException;
import java.util.Set;

import static ru.sevastopall.http.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    public static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, IMAGES);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var requestURI = ((HttpServletRequest) request).getRequestURI();
        if (isPublicPath(requestURI) || isUserLoggedIn(request)) {
            chain.doFilter(request, response);
        } else {
            var previousPage = ((HttpServletRequest) request).getHeader("referer");
            ((HttpServletResponse) response).sendRedirect(previousPage != null ? previousPage: LOGIN);
        }
    }

    private boolean isUserLoggedIn(ServletRequest request) {
        var user =(UserDto)((HttpServletRequest) request).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPublicPath(String requestURI) {
        return PUBLIC_PATH.stream().anyMatch(requestURI::startsWith);
    }
}
