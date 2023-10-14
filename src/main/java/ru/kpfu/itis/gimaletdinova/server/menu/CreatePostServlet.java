package ru.kpfu.itis.gimaletdinova.server.menu;

import ru.kpfu.itis.gimaletdinova.Const;
import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.model.Post;
import ru.kpfu.itis.gimaletdinova.util.FileDownloadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "createPostServlet", urlPatterns = "/posts/add")
@MultipartConfig(
        maxFileSize = Const.maxFileSize,
        maxRequestSize = Const.maxRequestSize
)
public class CreatePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("create_post.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("img");
        String img = FileDownloadUtil.saveImg(part);
        String title = req.getParameter("title");
        String text = req.getParameter("text");

        if (title.isEmpty()) {
            req.setAttribute("empty_title_error", true);
            req.getRequestDispatcher("create_post.ftl").forward(req, resp);
        } else {
            Dao<Post> postDao = (Dao<Post>) getServletContext().getAttribute(KeyNames.POST_DAO);
            postDao.save(new Post(title, text, img,
                    Integer.parseInt(req.getSession().getAttribute("user_id").toString())));
            resp.sendRedirect("my_posts.ftl");
        }
    }
}
