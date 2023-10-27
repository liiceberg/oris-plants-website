package ru.kpfu.itis.gimaletdinova.dao.implementations;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {
    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public Boolean isExist(String login) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select id from users where login='" + login + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet.next();
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "update users set name=?, lastname=?, img=?, password=? where id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLastname());
        preparedStatement.setString(3, user.getImg());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setInt(5, user.getId());
        preparedStatement.executeUpdate();
    }

    public User get(String login) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from users where login='" + login + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.next();
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("lastname"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("img")
        );

    }

    @Override
    public User get(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from users where id=" + id;
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("lastname"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("img")
        );

    }

    @Override
    public List<User> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * from users";
        ResultSet resultSet = statement.executeQuery(sql);
        List<User> users = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("img")
                        )
                );
            }
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = "insert into users (name, lastname, login, password) values (?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLastname());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.setString(4, user.getPassword());

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from users where id=" + id;
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

    }
}
