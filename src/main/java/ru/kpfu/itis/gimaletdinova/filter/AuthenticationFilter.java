package ru.kpfu.itis.gimaletdinova.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(filterName = "authenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession == null) {
            Cookie[] cookies = httpServletRequest.getCookies();
            if (httpServletRequest.getCookies() != null) {
                Optional<Cookie> optionalCookie = Arrays
                        .stream(cookies)
                        .filter(c -> c.getName().equals("saved_user_id"))
                        .findAny();
                if (optionalCookie.isPresent()) {
                    httpServletRequest.getSession().setAttribute("user_id", optionalCookie.get().getValue());
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/profile");
                    return;
                }
            }
        }
        if (httpServletRequest.getSession().getAttribute("user_id") == null) {
            String uri = httpServletRequest.getRequestURI();
            if (uri.contains("myposts") || uri.contains("profile") || uri.contains("favourites")) {
                httpServletResponse.sendRedirect("/login");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
