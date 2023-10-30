package ru.kpfu.itis.gimaletdinova.server.authorization;

import ru.kpfu.itis.gimaletdinova.Const;
import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.util.PasswordUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = (UserDao) getServletContext().getAttribute(KeyNames.USER_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getSession().getAttribute("user_id") != null) {
            resp.sendRedirect(req.getContextPath() + "/profile#logout");
            return;
        }
        req.getRequestDispatcher("/login.ftl").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(Const.maxAge);

        try {
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
                    resp.sendRedirect(req.getContextPath() + "/myposts");
                    return;
                }

            }
        } catch (SQLException e) {
            req.setAttribute("db_error", true);
            req.getRequestDispatcher( "/login.ftl").forward(req, resp);
            return;
        }

        req.setAttribute("error", true);
        req.getRequestDispatcher( "/login.ftl").forward(req, resp);
    }
}
