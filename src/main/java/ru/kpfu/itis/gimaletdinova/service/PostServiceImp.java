package ru.kpfu.itis.gimaletdinova.service;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PostDao;
import ru.kpfu.itis.gimaletdinova.dto.PostDto;
import ru.kpfu.itis.gimaletdinova.model.Post;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PostServiceImp implements PostService {

    private final PostDao postDao;
    private final UserService userService;

    public PostServiceImp(Dao<Post> postDao, UserService userService) {
        this.postDao = (PostDao) postDao;
        this.userService = userService;
    }

    @Override
    public List<PostDto> getAll() {
        return postDao
                .getAll()
                .stream()
                .map(p -> new PostDto(p.getId(), p.getTitle(), p.getText(), p.getImg(), userService.get(p.getAuthorId()), getTime(p.getDateTime())))
                .collect(Collectors.toList());
    }
    @Override
    public PostDto get(int id) {
        Post p = postDao.get(id);

        return new PostDto(p.getId(), p.getTitle(), p.getText(), p.getImg(), userService.get(p.getAuthorId()), getTime(p.getDateTime()));
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    public List<PostDto> getAll(int userId) {
        return postDao
                .getAll(userId)
                .stream()
                .map(p -> new PostDto(p.getId(), p.getTitle(), p.getText(), p.getImg(), userService.get(p.getAuthorId()), getTime(p.getDateTime())))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        postDao.delete(id);
    }

    private String getTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("hh:MM   dd.MM.yyyy"));
    }
}
