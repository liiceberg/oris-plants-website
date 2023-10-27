package ru.kpfu.itis.gimaletdinova.dao.implementations;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.model.Post;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao implements Dao<Post> {
    private final Connection connection;

    public PostDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Post get(int id) throws SQLException {

        Statement statement = connection.createStatement();
        String sql = "select * from posts where id='" + id + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        return new Post(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getString("img"),
                resultSet.getInt("author_id"),
                resultSet.getTimestamp("datetime").toLocalDateTime()
        );
    }

    @Override
    public List<Post> getAll() throws SQLException {

        Statement statement = connection.createStatement();
        String sql = "SELECT * from posts order by datetime desc";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Post> posts = new ArrayList<>();

        while (resultSet.next()) {
            posts.add(
                    new Post(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("img"),
                            resultSet.getInt("author_id"),
                            resultSet.getTimestamp("datetime").toLocalDateTime()
                    )
            );
        }

        return posts;

    }

    @Override
    public void save(Post post) throws SQLException {
        String sql = "insert into posts (title, description, author_id, img) values (?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, post.getTitle());
        preparedStatement.setString(2, post.getText());
        preparedStatement.setInt(3, post.getAuthorId());
        preparedStatement.setString(4, post.getImg());

        preparedStatement.executeUpdate();

    }

    public List<Post> getAll(int userId) throws SQLException {

        Statement statement = connection.createStatement();
        String sql = "SELECT * from posts where author_id=" + userId + " order by datetime desc";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Post> posts = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                posts.add(
                        new Post(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getString("img"),
                                resultSet.getInt("author_id"),
                                resultSet.getTimestamp("datetime").toLocalDateTime()
                        )
                );
            }
        }
        return posts;

    }

    @Override
    public void update(Post post) throws SQLException {
        String sql = "update posts set title=?, description=?, img=? where id=?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, post.getTitle());
        preparedStatement.setString(2, post.getText());
        preparedStatement.setString(3, post.getImg());
        preparedStatement.setInt(4, post.getId());

        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from posts where id=" + id;

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}
