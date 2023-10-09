package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        if (req.getCookies() != null) {
            Optional<Cookie> optionalCookie  = Arrays
                    .stream(cookies)
                    .filter(c -> c.getName().equals("saved_user_id"))
                    .findAny();
            if (optionalCookie.isPresent()) {
                req.getSession().setAttribute("user_id", optionalCookie.get().getValue());
                resp.sendRedirect("/home");
            } else {
                resp.sendRedirect("login.ftl");
            }
        } else {
            resp.sendRedirect("login.ftl");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(-1);

        if (userDao.isExist(login)) {
            User user = userDao.get(login);
            if (user.getPassword().equals(PasswordUtil.encrypt(password))) {

                session.setAttribute("user_id", user.getId());

                String save = req.getParameter("save");

                if (save != null) {
                    Cookie cookie = new Cookie("saved_user_id", Integer.toString(user.getId()));
                    cookie.setMaxAge(-1);
                    resp.addCookie(cookie);
                }
                resp.sendRedirect("/home");
            }
            else {
                session.setAttribute("error",true);
                resp.sendRedirect("/login");
            }
        }
        else {
            session.setAttribute("error",true);
            resp.sendRedirect("/login");
        }
    }
}
