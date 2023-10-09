package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImplementation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        clearAttributes(session);

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password_2");


        boolean isCorrect = true;

        if (userDao.isExist(login)) {
            session.setAttribute("login_error",true);
            isCorrect = false;
        }
        if (!verifyPassword(password, session)) {
            isCorrect = false;
        }
        if (!password.equals(password2)) {
            session.setAttribute("passwords_not_equal_error", true);
            isCorrect = false;
        }
        if (isCorrect) {
            userService.save(new User(name, lastname, login, password));
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("signup.ftl");
        }
    }

    private boolean verifyPassword(String password, HttpSession session) {
        if (password.length() < 8) {
            session.setAttribute("password_length_error", true);
            return false;
        }
        if (password.matches("[a-zA-Z]*")) {
            session.setAttribute("password_dig_error", true);
            return false;
        }
        if (password.matches("\\d*")) {
            session.setAttribute("password_letters_error", true);
            return false;
        }
        return true;
    }

    private void clearAttributes(HttpSession session) {
        session.removeAttribute("login_error");
        session.removeAttribute("passwords_not_equal_error");
        session.removeAttribute("password_length_error");
        session.removeAttribute("password_letters_error");
        session.removeAttribute("password_dig_error");
    }
}
