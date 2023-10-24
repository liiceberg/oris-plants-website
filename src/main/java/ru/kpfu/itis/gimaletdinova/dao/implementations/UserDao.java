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

    public Boolean isExist(String login) {
        try {
            Statement statement = connection.createStatement();
            String sql = "select id from users where login='" + login + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(User user) {
        String sql = "update users set name=?, lastname=?, img=?, password=? where id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getImg());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User get(String login) {
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users where login='" + login + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User get(int id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users where id=" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(User user) {
        String sql = "insert into users (name, lastname, login, password) values (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from users where id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
