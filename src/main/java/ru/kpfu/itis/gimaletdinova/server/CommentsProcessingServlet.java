package ru.kpfu.itis.gimaletdinova.server;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.model.Comment;
import ru.kpfu.itis.gimaletdinova.dao.implementations.CommentDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "commentsServlet", urlPatterns = "/comment")
public class CommentsProcessingServlet extends HttpServlet {
    private Dao<Comment> dao;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = (CommentDao) getServletContext().getAttribute(KeyNames.COMMENT_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            dao.delete(id);
        } catch (SQLException ignored) {}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
