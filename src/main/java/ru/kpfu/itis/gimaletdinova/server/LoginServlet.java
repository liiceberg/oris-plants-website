package ru.kpfu.itis.gimaletdinova.server;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    public static final String LOGIN = "liceberg";
    public static final String PASSWORD = "54321";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) != null) {
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("login.ftl");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (LOGIN.equalsIgnoreCase(login) && PASSWORD.equals(password)) {

            HttpSession session = req.getSession();
            session.setAttribute("user", login);
            session.setMaxInactiveInterval(60 * 60);

            resp.sendRedirect("/");
        }
        else {
            resp.sendRedirect("/login");
        }
    }
}
