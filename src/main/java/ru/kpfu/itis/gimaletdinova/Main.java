package ru.kpfu.itis.gimaletdinova;

import ru.kpfu.itis.gimaletdinova.dao.implementations.DamageDao;
import ru.kpfu.itis.gimaletdinova.model.Damage;
import ru.kpfu.itis.gimaletdinova.model.enam.Category;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;
import ru.kpfu.itis.gimaletdinova.util.DatabaseConnectionUtil;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, IllegalEnumValueException {
        Connection c = DatabaseConnectionUtil.getConnection();
        DamageDao dao = new DamageDao(c);

        for (Damage d: dao.getAll()) {
            System.out.println(d.getCausativeAgentDescription());
        }
    }
}
