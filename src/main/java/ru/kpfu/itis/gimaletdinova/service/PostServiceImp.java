package ru.kpfu.itis.gimaletdinova.service;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dto.PostDto;
import ru.kpfu.itis.gimaletdinova.model.Post;
import ru.kpfu.itis.gimaletdinova.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class PostServiceImp implements PostService {

    private final Dao<Post> postDao;
    private final UserService userService;

    public PostServiceImp(Dao<Post> postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    @Override
    public List<PostDto> getAll() {
        return postDao
                .getAll()
                .stream()
                .map(p -> new PostDto(p.getTitle(), p.getText(), p.getImg(), userService.get(p.getAuthorId()), p.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto get(int id) {
        Post p = postDao.get(id);
        return new PostDto(p.getTitle(), p.getText(), p.getImg(), userService.get(p.getAuthorId()), p.getDateTime());
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }
}
