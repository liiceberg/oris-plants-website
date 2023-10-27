package ru.kpfu.itis.gimaletdinova.server;

import org.json.JSONObject;
import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dto.CommentDto;
import ru.kpfu.itis.gimaletdinova.dto.PostDto;
import ru.kpfu.itis.gimaletdinova.dto.UserDto;
import ru.kpfu.itis.gimaletdinova.model.Comment;
import ru.kpfu.itis.gimaletdinova.service.CommentServiceImp;
import ru.kpfu.itis.gimaletdinova.service.PostServiceImp;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "postViewingServlet", urlPatterns = "/post")
public class PostViewingServlet extends HttpServlet {

    private PostServiceImp postService;
    private CommentServiceImp commentService;
    private UserServiceImp userService;
    private int postId;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostServiceImp) getServletContext().getAttribute(KeyNames.POST_SERVICE);
        commentService = (CommentServiceImp) getServletContext().getAttribute(KeyNames.COMMENT_SERVICE);
        userService = (UserServiceImp) getServletContext().getAttribute(KeyNames.USER_SERVICE);
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
        String text = req.getParameter("text");
        if (text.isEmpty()) {
            return;
        }
        req.getAttribute("feedback");
        int authorId = Integer.parseInt(req.getSession().getAttribute("user_id").toString());
        Comment c = new Comment(authorId, text, postId);
        commentService.save(c);

        UserDto user = userService.get(authorId);
        String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:MM   dd.MM.yyyy"));

        CommentDto dto = new CommentDto(user, datetime, text);
        JSONObject json = new JSONObject(dto);
        resp.setContentType("application/json");
        resp.getWriter().write(json.toString());
    }
}

