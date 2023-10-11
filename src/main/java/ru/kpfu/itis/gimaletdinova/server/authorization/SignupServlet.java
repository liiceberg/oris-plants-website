package ru.kpfu.itis.gimaletdinova.server.authorization;

import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImplementation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="signupServlet", urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {

    private final UserDao userDao = new UserDao();
    private final UserServiceImplementation userService = new UserServiceImplementation();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("signup.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password_2");

        if (isFormCorrect(req, name, lastname, login, password, password2)) {
            userService.save(new User(name, lastname, login, password));
            resp.sendRedirect("/login");
        } else {
            req.getRequestDispatcher("signup.ftl").forward(req, resp);
        }
    }

    private boolean isFormCorrect(HttpServletRequest req, String name, String lastname,
                                  String login, String password, String password2) {
        if (name.isEmpty() || lastname.isEmpty() || login.isEmpty()) {
            req.setAttribute("empty_field_error", true);
            return false;
        }
        if (userDao.isExist(login)) {
            req.setAttribute("login_error",true);
            return false;
        }
        if (!verifyPassword(password, req)) {
            return false;
        }
        if (!password.equals(password2)) {
            req.setAttribute("passwords_not_equal_error", true);
            return false;
        }
        return true;
    }

    private boolean verifyPassword(String password, HttpServletRequest req) {
        if (password.length() < 8) {
            req.setAttribute("password_length_error", true);
            return false;
        }
        if (password.matches("[a-zA-Z]*")) {
            req.setAttribute("password_dig_error", true);
            return false;
        }
        if (password.matches("\\d*")) {
            req.setAttribute("password_letters_error", true);
            return false;
        }
        return true;
    }

}
