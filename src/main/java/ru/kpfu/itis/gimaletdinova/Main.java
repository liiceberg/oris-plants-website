package ru.kpfu.itis.gimaletdinova;

import ru.kpfu.itis.gimaletdinova.dao.implementations.*;
import ru.kpfu.itis.gimaletdinova.model.Comment;
import ru.kpfu.itis.gimaletdinova.model.Damage;
import ru.kpfu.itis.gimaletdinova.model.enam.Category;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;
import ru.kpfu.itis.gimaletdinova.service.CommentService;
import ru.kpfu.itis.gimaletdinova.service.DamageService;
import ru.kpfu.itis.gimaletdinova.service.PostService;
import ru.kpfu.itis.gimaletdinova.service.UserService;
import ru.kpfu.itis.gimaletdinova.service.implementations.CommentServiceImp;
import ru.kpfu.itis.gimaletdinova.service.implementations.DamageServiceImp;
import ru.kpfu.itis.gimaletdinova.service.implementations.PostServiceImp;
import ru.kpfu.itis.gimaletdinova.service.implementations.UserServiceImp;
import ru.kpfu.itis.gimaletdinova.util.DatabaseConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, IllegalEnumValueException {
        Connection connection = DatabaseConnectionUtil.getConnection();
        UserDao userDao = new UserDao(connection);
        PostDao postDao = new PostDao(connection);
        CommentDao commentDao = new CommentDao(connection);
//        PlantDao plantDao = new PlantDao(connection);
//        DamageDao damageDao = new DamageDao(connection);
        UserService userService = new UserServiceImp(userDao);
//        PostService postService = new PostServiceImp(postDao, userService);
        CommentService commentService = new CommentServiceImp(commentDao, userService);
//        DamageService damageService = new DamageServiceImp(damageDao);
//        postService.delete(21);
        commentService.save(new Comment(8, "text", 5, null));
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm   dd.MM.yyyy")));
    }
}
