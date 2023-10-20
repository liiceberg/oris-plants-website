package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dto.PostDto;
import ru.kpfu.itis.gimaletdinova.model.Comment;
import ru.kpfu.itis.gimaletdinova.service.CommentServiceImp;
import ru.kpfu.itis.gimaletdinova.service.PostServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "postViewingServlet", urlPatterns = "/post/*")
public class PostViewingServlet extends HttpServlet {

    private PostServiceImp postService;
    private CommentServiceImp commentService;
    private int postId;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostServiceImp) getServletContext().getAttribute(KeyNames.POST_SERVICE);
        commentService = (CommentServiceImp) getServletContext().getAttribute(KeyNames.COMMENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        postId = Integer.parseInt(req.getPathInfo().substring(1));

        PostDto dto = postService.get(postId);
        req.setAttribute("post", dto);

        req.setAttribute("comments", commentService.getAll(postId));

        req.getRequestDispatcher("/post.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getAttribute("text").toString();
        if (text.isEmpty()) {
            return;
        }
        req.getAttribute("feedback");
        int authorId = (Integer) req.getSession().getAttribute("user_id");
        Comment c = new Comment(authorId, text, postId);
        commentService.save(c);
    }
}
