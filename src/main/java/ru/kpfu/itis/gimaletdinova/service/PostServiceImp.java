package ru.kpfu.itis.gimaletdinova.service;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PostDao;
import ru.kpfu.itis.gimaletdinova.dto.PostDto;
import ru.kpfu.itis.gimaletdinova.model.Post;

import java.sql.SQLException;
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
        try {
            return postDao
                    .getAll()
                    .stream()
                    .map(p -> new PostDto(p.getId(), p.getTitle(), p.getText(), p.getImg(), userService.get(p.getAuthorId()), getTime(p.getDateTime())))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public PostDto get(int id) {
        try {
            Post p = postDao.get(id);
            return new PostDto(p.getId(), p.getTitle(), p.getText(), p.getImg(), userService.get(p.getAuthorId()), getTime(p.getDateTime()));
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean save(Post post) {
        try {
            postDao.save(post);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<PostDto> getAll(int userId) {
        try {
            return postDao
                    .getAll(userId)
                    .stream()
                    .map(p -> new PostDto(p.getId(), p.getTitle(), p.getText(), p.getImg(), userService.get(p.getAuthorId()), getTime(p.getDateTime())))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            postDao.delete(id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private String getTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("HH:MM   dd.MM.yyyy"));
    }
}
