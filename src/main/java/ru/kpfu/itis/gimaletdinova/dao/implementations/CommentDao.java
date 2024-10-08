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
    public Comment get(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from comments where id='" + id + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        return new Comment(
                resultSet.getInt("id"),
                resultSet.getInt("author_id"),
                resultSet.getTimestamp("datetime").toLocalDateTime(),
                resultSet.getString("comment_text"),
                resultSet.getInt("post_id"),
                resultSet.getInt("feedback_comment_id")
        );

    }

    @Override
    public List<Comment> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * from comments order by datetime";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Comment> comments = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                int id = resultSet.getInt("feedback_comment_id");
                comments.add(
                        new Comment(
                                resultSet.getInt("id"),
                                resultSet.getInt("author_id"),
                                resultSet.getTimestamp("datetime").toLocalDateTime(),
                                resultSet.getString("comment_text"),
                                resultSet.getInt("post_id"),
                                id == 0? null: id
                        )
                );
            }
        }
        return comments;
    }

    @Override
    public void save(Comment comment) throws SQLException {
        String sql = "insert into comments (author_id, comment_text, post_id, feedback_comment_id) values (?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, comment.getAuthorId());
        preparedStatement.setString(2, comment.getText());
        preparedStatement.setInt(3, comment.getPostId());
        preparedStatement.setObject(4, comment.getFeedbackCommentId());

        preparedStatement.executeUpdate();

    }

    public List<Comment> getAll(int postId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * from comments where post_id=" + postId + " order by datetime desc";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Comment> comments = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("feedback_comment_id");
            comments.add(
                    new Comment(
                            resultSet.getInt("id"),
                            resultSet.getInt("author_id"),
                            resultSet.getTimestamp("datetime").toLocalDateTime(),
                            resultSet.getString("comment_text"),
                            resultSet.getInt("post_id"),
                            id == 0? null: id
                    )
            );
        }

        return comments;

    }

    @Override
    public void update(Comment comment) throws SQLException {
        String sql = "update comments set comment_text=? where id=?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, comment.getText());
        preparedStatement.setInt(2, comment.getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from comments where id=" + id;
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public Comment getLast(int userId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from comments where author_id=" + userId + " ORDER BY datetime DESC LIMIT 1 ";
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        int id = resultSet.getInt("feedback_comment_id");
        return new Comment(
                resultSet.getInt("id"),
                resultSet.getInt("author_id"),
                resultSet.getTimestamp("datetime").toLocalDateTime(),
                resultSet.getString("comment_text"),
                resultSet.getInt("post_id"),
                id == 0? null: id
        );

    }
}
