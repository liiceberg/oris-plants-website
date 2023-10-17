package ru.kpfu.itis.gimaletdinova.service;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.CommentDao;
import ru.kpfu.itis.gimaletdinova.dto.CommentDto;
import ru.kpfu.itis.gimaletdinova.model.Comment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CommentServiceImp implements CommentService {

    private final CommentDao commentDao;
    private final UserService userService;
    private final PostService postService;

    public CommentServiceImp(Dao<Comment> commentDao, UserService userService, PostService postService) {
        this.commentDao = (CommentDao) commentDao;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public List<CommentDto> getAll() {
        return commentDao
                .getAll()
                .stream()
                .map(c -> new CommentDto(
                        userService.get(c.getAuthorId()),
                        getTime(c.getDateTime()),
                        c.getText(),
                        postService.get(c.getPostId()),
                        userService.get(c.getFeedbackUserId())))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto get(int id) {
        Comment c = commentDao.get(id);
        return new CommentDto(
                userService.get(c.getAuthorId()),
                getTime(c.getDateTime()),
                c.getText(),
                postService.get(c.getPostId()),
                userService.get(c.getFeedbackUserId()));
    }

    @Override
    public void save(Comment c) {
        commentDao.save(c);
    }

    public List<CommentDto> getAll(int postId) {
        return commentDao
                .getAll(postId)
                .stream()
                .map(c -> new CommentDto(
                        userService.get(c.getAuthorId()),
                        getTime(c.getDateTime()),
                        c.getText(),
                        postService.get(c.getPostId()),
                        userService.get(c.getFeedbackUserId())))
                .collect(Collectors.toList());
    }

    private String getTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("hh:MM dd.MM.yyyy"));
    }
}
