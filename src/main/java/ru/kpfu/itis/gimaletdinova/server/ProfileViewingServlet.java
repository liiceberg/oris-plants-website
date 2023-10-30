package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dto.UserDto;
import ru.kpfu.itis.gimaletdinova.service.implementations.UserServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profileViewingServlet", urlPatterns = "/view/*")
public class ProfileViewingServlet extends HttpServlet {
    private UserServiceImp userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserServiceImp) getServletContext().getAttribute(KeyNames.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = userService.get(req.getParameter("login"));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/profile_view.ftl").forward(req, resp);
    }
}
