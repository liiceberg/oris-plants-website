package ru.kpfu.itis.gimaletdinova;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String s = "1234wer1234";
        System.out.println(s.matches("\\d*"));
    }
}
