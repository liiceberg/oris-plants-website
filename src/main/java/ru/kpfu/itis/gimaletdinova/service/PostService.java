package ru.kpfu.itis.gimaletdinova.service;

import ru.kpfu.itis.gimaletdinova.dto.PostDto;
import ru.kpfu.itis.gimaletdinova.model.Post;

import java.util.List;

public interface PostService {
    List<PostDto> getAll();
    PostDto get(int id);
    boolean save(Post post);
    boolean delete(int id);
}
