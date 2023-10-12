package ru.kpfu.itis.gimaletdinova.server.menu;

import ru.kpfu.itis.gimaletdinova.dto.UserDto;
import ru.kpfu.itis.gimaletdinova.service.UserService;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    private final UserService userService = new UserServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getSession().getAttribute("user_id").toString());
        UserDto userDto = userService.get(userId);
        req.setAttribute("user", userDto);
        req.getRequestDispatcher("menu/profile.ftl").forward(req, resp);
    }

}
