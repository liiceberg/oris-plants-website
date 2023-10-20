package ru.kpfu.itis.gimaletdinova.server.authorization;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.service.UserService;
import ru.kpfu.itis.gimaletdinova.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name="signupServlet", urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/signup.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password_2");

        if (isFormCorrect(req, name, lastname, login, password, password2)) {
            UserService userService = (UserService) getServletContext().getAttribute(KeyNames.USER_SERVICE);
            userService.save(new User(name, lastname, login, password));
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("/signup.ftl").forward(req, resp);
        }
    }

    private boolean isFormCorrect(HttpServletRequest req, String name, String lastname,
                                  String login, String password, String password2) {
        if (name.isEmpty() || lastname.isEmpty() || login.isEmpty()) {
            req.setAttribute("empty_field_error", true);
            return false;
        }
        UserDao userDao = (UserDao) getServletContext().getAttribute(KeyNames.USER_DAO);
        if (userDao.isExist(login)) {
            req.setAttribute("login_error",true);
            return false;
        }
        Map<String, Boolean> map = PasswordUtil.verify(password);
        for (String error: map.keySet()) {
            if (map.get(error)) {
                req.setAttribute(error, true);
                return false;
            }
        }
        if (!PasswordUtil.verifyEquals(password, password2)) {
            req.setAttribute("passwords_not_equal_error", true);
            return false;
        }
        return true;
    }

}
