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
                        resultSet.getString("img"),
                        resultSet.getInt("author_id"),
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
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from posts order by datetime desc";
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Post post) {
        String sql = "insert into posts (title, description, author_id, img) values (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setInt(3, post.getAuthorId());
            preparedStatement.setString(4, post.getImg());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Post> getAll(int userId) {
        try {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Post post) {
        String sql = "update posts set title=?, description=?, img=? where id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getImg());
            preparedStatement.setInt(4, post.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from posts where id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
