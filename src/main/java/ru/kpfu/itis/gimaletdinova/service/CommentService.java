package ru.kpfu.itis.gimaletdinova.service;

import ru.kpfu.itis.gimaletdinova.dto.CommentDto;
import ru.kpfu.itis.gimaletdinova.model.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAll(int postId);
    CommentDto get(int id);
    boolean save(Comment comment);
    CommentDto getLast(int userId);
}
