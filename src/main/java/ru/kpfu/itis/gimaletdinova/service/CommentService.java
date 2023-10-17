package ru.kpfu.itis.gimaletdinova.service;

import ru.kpfu.itis.gimaletdinova.dto.CommentDto;
import ru.kpfu.itis.gimaletdinova.model.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAll();
    CommentDto get(int id);
    void save(Comment post);
}
