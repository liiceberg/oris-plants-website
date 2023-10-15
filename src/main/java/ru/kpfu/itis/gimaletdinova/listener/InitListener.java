package ru.kpfu.itis.gimaletdinova.listener;

import ru.kpfu.itis.gimaletdinova.KeyNames;
import ru.kpfu.itis.gimaletdinova.dao.implementations.PostDao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.service.PostServiceImp;
import ru.kpfu.itis.gimaletdinova.service.UserService;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImp;
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
        UserService userService = new UserServiceImp(userDao);
        sce.getServletContext().setAttribute(KeyNames.USER_DAO, userDao);
        sce.getServletContext().setAttribute(KeyNames.POST_DAO, postDao);
        sce.getServletContext().setAttribute(KeyNames.USER_SERVICE, userService);
        sce.getServletContext().setAttribute(KeyNames.POST_SERVICE, new PostServiceImp(postDao, userService));
    }
}
