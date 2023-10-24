package ru.kpfu.itis.gimaletdinova.server.menu;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.service.PostServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="homeServlet", urlPatterns = "/myposts")
public class HomeServlet extends HttpServlet {

    private PostServiceImp postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostServiceImp) getServletContext().getAttribute(KeyNames.POST_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getSession().getAttribute("user_id").toString());
        req.setAttribute("posts", postService.getAll(userId));
        req.getRequestDispatcher("/menu/home.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getParameter("id"));
        postService.delete(postId);
    }
}
