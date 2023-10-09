package ru.kpfu.itis.gimaletdinova;

import ru.kpfu.itis.gimaletdinova.service.UserService;
import ru.kpfu.itis.gimaletdinova.service.UserServiceImplementation;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImplementation();
        System.out.println(userService.get(8));

    }
}
