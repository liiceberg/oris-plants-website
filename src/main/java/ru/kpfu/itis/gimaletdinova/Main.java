package ru.kpfu.itis.gimaletdinova;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.gimaletdinova.dao.implementations.CommentDao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PostDao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.dto.PostDto;
import ru.kpfu.itis.gimaletdinova.model.Post;
import ru.kpfu.itis.gimaletdinova.model.plant_enam.Level;
import ru.kpfu.itis.gimaletdinova.service.CommentServiceImp;
import ru.kpfu.itis.gimaletdinova.service.PostService;
import ru.kpfu.itis.gimaletdinova.service.PostServiceImp;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImp;
import ru.kpfu.itis.gimaletdinova.util.CloudinaryUtil;
import ru.kpfu.itis.gimaletdinova.util.DatabaseConnectionUtil;
import ru.kpfu.itis.gimaletdinova.util.PasswordUtil;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Connection c = DatabaseConnectionUtil.getConnection();
        CommentServiceImp commentService = new CommentServiceImp(new CommentDao(c), new UserServiceImp(new UserDao(c)), new PostServiceImp(new PostDao(c), new UserServiceImp(new UserDao(c))));
         commentService.getAll(1);
    }
}
