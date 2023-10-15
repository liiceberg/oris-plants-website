package ru.kpfu.itis.gimaletdinova.server.menu;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.service.PostServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helpServlet", urlPatterns = "/help")
public class HelpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostServiceImp postService = (PostServiceImp) getServletContext().getAttribute(KeyNames.POST_SERVICE);
        req.setAttribute("posts", postService.getAll());
        req.getRequestDispatcher("menu/help.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("menu/help.ftl");
    }
}
