package ru.kpfu.itis.gimaletdinova.dao.implementations;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.model.Plant;
import ru.kpfu.itis.gimaletdinova.model.plant_enam.Category;
import ru.kpfu.itis.gimaletdinova.model.plant_enam.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantDao implements Dao<Plant> {

    private final Connection connection;

    public PlantDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Plant get(int id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from plants where id='" + id + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                resultSet.next();
                return new Plant(
                        resultSet.getInt("id"),
                        Category.valueOf(resultSet.getString("category")),
                        resultSet.getString("name"),
                        resultSet.getString("img"),
                        resultSet.getString("origin"),
                        resultSet.getString("description"),
                        resultSet.getInt("high"),
                        Level.valueOf(resultSet.getInt("light")),
                        Level.valueOf(resultSet.getInt("watering")),
                        resultSet.getBoolean("toxicity"),
                        Level.valueOf(resultSet.getInt("care_difficulty"))

                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Plant> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from plants order by category";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Plant> plants = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    plants.add(
                            new Plant(
                                    resultSet.getInt("id"),
                                    Category.valueOf(resultSet.getString("category")),
                                    resultSet.getString("name"),
                                    resultSet.getString("img"),
                                    resultSet.getString("origin"),
                                    resultSet.getString("description"),
                                    resultSet.getInt("high"),
                                    Level.valueOf(resultSet.getInt("light")),
                                    Level.valueOf(resultSet.getInt("watering")),
                                    resultSet.getBoolean("toxicity"),
                                    Level.valueOf(resultSet.getInt("care_difficulty"))

                            )
                    );
                }
            }
            return plants;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Plant plant) {
        String sql = "insert into plants (category, name, img, origin, description, high, light, watering," +
                " toxicity, care_difficulty) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, plant.getCategory().name());
            preparedStatement.setString(2, plant.getName());
            preparedStatement.setString(3, plant.getImg());
            preparedStatement.setString(4, plant.getOrigin());
            preparedStatement.setString(5, plant.getDescription());
            preparedStatement.setInt(6, plant.getHigh());
            preparedStatement.setInt(7, plant.getLight().getValue());
            preparedStatement.setInt(8, plant.getWatering().getValue());
            preparedStatement.setBoolean(9, plant.getToxicity());
            preparedStatement.setInt(10, plant.getCareDifficulty().getValue());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Plant plant) {
        String sql = "update plants set category=?, name=?, img=?, origin=?, description=?, high=?, light=?," +
                " watering=?, toxicity=?, care_difficulty=? where id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, plant.getCategory().name());
            preparedStatement.setString(2, plant.getName());
            preparedStatement.setString(3, plant.getImg());
            preparedStatement.setString(4, plant.getOrigin());
            preparedStatement.setString(5, plant.getDescription());
            preparedStatement.setInt(6, plant.getHigh());
            preparedStatement.setInt(7, plant.getLight().getValue());
            preparedStatement.setInt(8, plant.getWatering().getValue());
            preparedStatement.setBoolean(9, plant.getToxicity());
            preparedStatement.setInt(10, plant.getCareDifficulty().getValue());
            preparedStatement.setInt(11, plant.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Plant plant) {
        String sql = "delete from plans where id=" + plant.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
