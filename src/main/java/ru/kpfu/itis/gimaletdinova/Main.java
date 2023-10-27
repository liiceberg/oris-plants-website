package ru.kpfu.itis.gimaletdinova;

import ru.kpfu.itis.gimaletdinova.dao.implementations.CommentDao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PlantDao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PostDao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.model.enam.Category;
import ru.kpfu.itis.gimaletdinova.service.CommentServiceImp;
import ru.kpfu.itis.gimaletdinova.service.PostServiceImp;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImp;
import ru.kpfu.itis.gimaletdinova.util.DatabaseConnectionUtil;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
//        Connection c = DatabaseConnectionUtil.getConnection();
//        PlantDao plantDao = new PlantDao(c);
//        plantDao.addFavourite(8, 2);
        Category[] c = Category.values();
        for (Category category: c) {
            System.out.println(category.getName());
        }
    }
}
