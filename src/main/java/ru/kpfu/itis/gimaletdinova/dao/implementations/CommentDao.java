package ru.kpfu.itis.gimaletdinova.dao.implementations;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements Dao<Comment> {
    private final Connection connection;

    public CommentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Comment get(int id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from comments where id='" + id + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                resultSet.next();
                return new Comment(
                        resultSet.getInt("id"),
                        resultSet.getInt("author_id"),
                        resultSet.getTimestamp("datetime").toLocalDateTime(),
                        resultSet.getString("comment_text"),
                        resultSet.getInt("post_id"),
                        resultSet.getInt("feedback_user_id")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Comment> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from comments";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Comment> comments = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    comments.add(
                            new Comment(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("author_id"),
                                    resultSet.getTimestamp("datetime").toLocalDateTime(),
                                    resultSet.getString("comment_text"),
                                    resultSet.getInt("post_id"),
                                    resultSet.getInt("feedback_user_id")
                            )
                    );
                }
            }
            return comments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Comment comment) {
        String sql = "insert into comments (author_id, comment_text, post_id, feedback_user_id) values (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, comment.getAuthorId());
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setInt(3, comment.getPostId());
            preparedStatement.setInt(4, comment.getFeedbackUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Comment> getAll(int postId) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from comments where post_id=" + postId;
            ResultSet resultSet = statement.executeQuery(sql);
            List<Comment> comments = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    comments.add(
                            new Comment(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("author_id"),
                                    resultSet.getTimestamp("datetime").toLocalDateTime(),
                                    resultSet.getString("comment_text"),
                                    resultSet.getInt("post_id")
//                                    resultSet.getInt("feedback_user_id")
                            )
                    );
                }
            }
            return comments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Comment comment) {
        String sql = "update comments set comment_text=? where id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setInt(2, comment.getId());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from comments where id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
