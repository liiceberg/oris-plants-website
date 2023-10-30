package ru.kpfu.itis.gimaletdinova.service.implementations;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.CommentDao;
import ru.kpfu.itis.gimaletdinova.dto.CommentDto;
import ru.kpfu.itis.gimaletdinova.model.Comment;
import ru.kpfu.itis.gimaletdinova.service.CommentService;
import ru.kpfu.itis.gimaletdinova.service.UserService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CommentServiceImp implements CommentService {

    private final CommentDao commentDao;
    private final UserService userService;

    public CommentServiceImp(Dao<Comment> commentDao, UserService userService) {
        this.commentDao = (CommentDao) commentDao;
        this.userService = userService;
    }


    public List<CommentDto> getAll() {
        try {
            return commentDao
                    .getAll()
                    .stream()
                    .map(c -> new CommentDto(
                            c.getId(),
                            userService.get(c.getAuthorId()),
                            getTime(c.getDateTime()),
                            c.getText(),
                            c.getFeedbackCommentId()))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public CommentDto get(int id) {
        try {
            Comment c = commentDao.get(id);
            return new CommentDto(
                    c.getId(),
                    userService.get(c.getAuthorId()),
                    getTime(c.getDateTime()),
                    c.getText(),
                    c.getFeedbackCommentId());
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean save(Comment c) {
        try {
            commentDao.save(c);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<CommentDto> getAll(int postId) {
        try {
            return commentDao
                    .getAll(postId)
                    .stream()
                    .map(c -> new CommentDto(
                            c.getId(),
                            userService.get(c.getAuthorId()),
                            getTime(c.getDateTime()),
                            c.getText(),
                            c.getFeedbackCommentId()))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            return null;
        }
    }

    private String getTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("HH:mm   dd.MM.yyyy"));
    }

    public CommentDto getLast(int userId) {
        try {
            Comment c = commentDao.getLast(userId);
            return new CommentDto(
                    c.getId(),
                    userService.get(c.getAuthorId()),
                    getTime(c.getDateTime()),
                    c.getText(),
                    c.getFeedbackCommentId());
        } catch (SQLException e) {
            return null;
        }
    }
}