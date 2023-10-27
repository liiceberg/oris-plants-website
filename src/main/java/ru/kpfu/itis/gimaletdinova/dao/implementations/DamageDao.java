package ru.kpfu.itis.gimaletdinova.dao.implementations;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.model.Damage;
import ru.kpfu.itis.gimaletdinova.model.enam.CausativeAgent;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DamageDao implements Dao<Damage> {
    private final Connection connection;

    public DamageDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Damage get(int id) throws SQLException, IllegalEnumValueException {
        Statement statement = connection.createStatement();
        String sql = "select * from damages where id='" + id + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return new Damage(
                resultSet.getInt("id"),
                CausativeAgent.valueOf(resultSet.getInt("causative_agent")),
                resultSet.getString("description"),
                resultSet.getString("symptoms"),
                resultSet.getString("control_measures"),
                resultSet.getString("img")
        );
    }

    @Override
    public List<Damage> getAll() throws SQLException, IllegalEnumValueException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * from damages order by description";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Damage> damages = new ArrayList<>();

            while (resultSet.next()) {
                damages.add(
                        new Damage(
                                resultSet.getInt("id"),
                                CausativeAgent.valueOf(resultSet.getInt("causative_agent")),
                                resultSet.getString("description"),
                                resultSet.getString("symptoms"),
                                resultSet.getString("control_measures"),
                                resultSet.getString("img")
                        )
                );
            }

        return damages;
    }

    @Override
    public void save(Damage damage) throws SQLException {
        String sql = "insert into damages (causative_agent, description, symptoms, control_measures, img) values (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, damage.getCausativeAgentType().getValue());
        preparedStatement.setString(2, damage.getCausativeAgentDescription());
        preparedStatement.setString(3, damage.getSymptoms());
        preparedStatement.setString(4, damage.getControlMeasures());
        preparedStatement.setString(5, damage.getImg());

        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Damage damage) throws SQLException {
        String sql = "update damages set causative_agent=?, description=?, symptoms=?, control_measures=?, img=? where id=?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, damage.getCausativeAgentType().getValue());
        preparedStatement.setString(2, damage.getCausativeAgentDescription());
        preparedStatement.setString(3, damage.getSymptoms());
        preparedStatement.setString(4, damage.getControlMeasures());
        preparedStatement.setString(5, damage.getImg());
        preparedStatement.setInt(6, damage.getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from damages where id=" + id;
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
}
