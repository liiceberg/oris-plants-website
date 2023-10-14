package ru.kpfu.itis.gimaletdinova.server.authorization;

import ru.kpfu.itis.gimaletdinova.Const;
import ru.kpfu.itis.gimaletdinova.KeyNames;
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
        session.setMaxInactiveInterval(Const.maxAge);

        UserDao userDao = (UserDao) getServletContext().getAttribute(KeyNames.USER_DAO);

        if (userDao.isExist(login)) {
            User user = userDao.get(login);
            if (user.getPassword().equals(PasswordUtil.encrypt(password))) {

                session.setAttribute("user_id", user.getId());

                String save = req.getParameter("save");

                if (save != null) {
                    Cookie cookie = new Cookie("saved_user_id", Integer.toString(user.getId()));
                    cookie.setMaxAge(Const.maxAge);
                    resp.addCookie(cookie);
                }
                resp.sendRedirect("/home");
            }
            else {
                req.setAttribute("error",true);
                req.getRequestDispatcher("login.ftl").forward(req, resp);
            }
        }
        else {
            req.setAttribute("error",true);
            req.getRequestDispatcher("login.ftl").forward(req, resp);
        }
    }
}
