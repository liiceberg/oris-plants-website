package ru.kpfu.itis.gimaletdinova.listener;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.implementations.*;
import ru.kpfu.itis.gimaletdinova.service.*;
import ru.kpfu.itis.gimaletdinova.service.implementations.CommentServiceImp;
import ru.kpfu.itis.gimaletdinova.service.implementations.DamageServiceImp;
import ru.kpfu.itis.gimaletdinova.service.implementations.PostServiceImp;
import ru.kpfu.itis.gimaletdinova.service.implementations.UserServiceImp;
import ru.kpfu.itis.gimaletdinova.util.DatabaseConnectionUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection connection = DatabaseConnectionUtil.getConnection();
        UserDao userDao = new UserDao(connection);
        PostDao postDao = new PostDao(connection);
        CommentDao commentDao = new CommentDao(connection);
        PlantDao plantDao = new PlantDao(connection);
        DamageDao damageDao = new DamageDao(connection);
        UserService userService = new UserServiceImp(userDao);
        PostService postService = new PostServiceImp(postDao, userService);
        CommentService commentService = new CommentServiceImp(commentDao, userService);
        DamageService damageService = new DamageServiceImp(damageDao);

        sce.getServletContext().setAttribute(KeyNames.USER_DAO, userDao);
        sce.getServletContext().setAttribute(KeyNames.POST_DAO, postDao);
        sce.getServletContext().setAttribute(KeyNames.COMMENT_DAO, commentDao);
        sce.getServletContext().setAttribute(KeyNames.PLANT_DAO, plantDao);
        sce.getServletContext().setAttribute(KeyNames.DAMAGE_DAO, damageDao);
        sce.getServletContext().setAttribute(KeyNames.USER_SERVICE, userService);
        sce.getServletContext().setAttribute(KeyNames.POST_SERVICE, postService);
        sce.getServletContext().setAttribute(KeyNames.COMMENT_SERVICE, commentService);
        sce.getServletContext().setAttribute(KeyNames.DAMAGE_SERVICE, damageService);
    }
}
