package ru.kpfu.itis.gimaletdinova.dao;

import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    T get(int id) throws IllegalEnumValueException, SQLException;
    List<T> getAll() throws SQLException, IllegalEnumValueException;
    void save(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(int id) throws SQLException;

}
