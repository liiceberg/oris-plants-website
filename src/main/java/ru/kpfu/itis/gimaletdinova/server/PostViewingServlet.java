package ru.kpfu.itis.gimaletdinova.server;

import org.json.JSONObject;
import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.Messages;
import ru.kpfu.itis.gimaletdinova.dto.CommentDto;
import ru.kpfu.itis.gimaletdinova.dto.PostDto;
import ru.kpfu.itis.gimaletdinova.model.Comment;
import ru.kpfu.itis.gimaletdinova.service.implementations.CommentServiceImp;
import ru.kpfu.itis.gimaletdinova.service.implementations.PostServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "postViewingServlet", urlPatterns = "/post")
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

        postId = Integer.parseInt(req.getParameter("id"));

        PostDto dto = postService.get(postId);
        req.setAttribute("post", dto);

        req.setAttribute("comments", commentService.getAll(postId));

        req.getRequestDispatcher("/post.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");

        Object userId = req.getSession().getAttribute("user_id");
        if (userId == null) {
            JSONObject obj = new JSONObject();
            obj.put("exc", Messages.COMMENT_NOT_AVAILABLE_MESSAGE);
            resp.getWriter().write(obj.toString());
            return;
        }
        String text = req.getParameter("text");
        if (text.isEmpty()) {
            return;
        }
        int authorId = Integer.parseInt(userId.toString());
        String feedbackCommentId = req.getParameter("feedback");
        Comment c = new Comment(
                authorId,
                text,
                postId,
                feedbackCommentId.isEmpty() ? null: Integer.parseInt(feedbackCommentId));
        commentService.save(c);

        CommentDto dto = commentService.getLast(authorId);
        JSONObject json = new JSONObject(dto);
        resp.getWriter().write(json.toString());
    }
}

