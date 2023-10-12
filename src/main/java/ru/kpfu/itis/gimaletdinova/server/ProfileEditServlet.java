package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.dto.UserDto;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.service.UserService;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "profileEditServlet", urlPatterns = "/edit")
public class ProfileEditServlet extends HttpServlet {
    private final UserService userService = new UserServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = getUserId(req);
        UserDto userDto = userService.get(userId);
        req.setAttribute("user", userDto);
        req.getRequestDispatcher("profile_edit.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object img = req.getAttribute("img_url");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");

        if (name.isEmpty() || lastname.isEmpty()) {
            req.setAttribute("empty_field_error", true);
            req.getRequestDispatcher("profile_edit.ftl").forward(req, resp);
        } else {
            UserDao userDao = new UserDao();
            User user = userDao.get(getUserId(req));
            user.setName(name);
            user.setLastname(lastname);
            if (img != null) {
                user.setImg(img.toString());
            }
            userDao.update(user);

            resp.sendRedirect("/profile");
        }
    }

    private int getUserId(HttpServletRequest req) {
        return Integer.parseInt(req.getSession().getAttribute("user_id").toString());
    }
}
