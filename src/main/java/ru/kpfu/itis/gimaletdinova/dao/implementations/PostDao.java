package ru.kpfu.itis.gimaletdinova.dao.implementations;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.model.Post;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostDao implements Dao<Post> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();
    private final Dao<User> userDao = new UserDao();

    @Override
    public Post get(int id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from posts where id='" + id + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                resultSet.next();
                return new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        userDao.get(resultSet.getInt("author_id")),
                        resultSet.getTimestamp("datetime").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public void save(Post post) {

    }

    @Override
    public void update(Post post) {

    }
}
