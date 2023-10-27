package ru.kpfu.itis.gimaletdinova.dao.implementations;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.model.Plant;
import ru.kpfu.itis.gimaletdinova.model.enam.Category;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;
import ru.kpfu.itis.gimaletdinova.model.enam.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantDao implements Dao<Plant> {

    private final Connection connection;

    public PlantDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Plant get(int id) throws SQLException, IllegalEnumValueException {
        Statement statement = connection.createStatement();
        String sql = "select * from plants where id='" + id + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return new Plant(
                resultSet.getInt("id"),
                Category.getCategory(resultSet.getString("category")),
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

    @Override
    public List<Plant> getAll() throws SQLException, IllegalEnumValueException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * from plants order by name";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Plant> plants = new ArrayList<>();

        while (resultSet.next()) {
            plants.add(
                    new Plant(
                            resultSet.getInt("id"),
                            Category.getCategory(resultSet.getString("category")),
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

        return plants;
    }

    @Override
    public void save(Plant plant) throws SQLException {
        String sql = "insert into plants (category, name, img, origin, description, high, light, watering," +
                " toxicity, care_difficulty) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
    }

    @Override
    public void update(Plant plant) throws SQLException {
        String sql = "update plants set category=?, name=?, img=?, origin=?, description=?, high=?, light=?," +
                " watering=?, toxicity=?, care_difficulty=? where id=?;";

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
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from plans where id=" + id;
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public List<Plant> getAll(int userId) throws SQLException, IllegalEnumValueException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * from favourites inner join plants on favourites.card_id=plants.id where user_id="
                + userId;
        ResultSet resultSet = statement.executeQuery(sql);
        List<Plant> plants = new ArrayList<>();
        while (resultSet.next()) {
            plants.add(
                    new Plant(
                            resultSet.getInt("id"),
                            Category.getCategory(resultSet.getString("category")),
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
        return plants;
    }

    public List<Integer> getFavourites(int userId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT card_id from favourites where user_id=" + userId;
        ResultSet resultSet = statement.executeQuery(sql);
        List<Integer> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getInt("card_id"));
        }
        return list;
    }

    public void addFavourite(int userId, int cardId) throws SQLException {
        String sql = "insert into favourites (user_id, card_id) values (?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, cardId);
        preparedStatement.executeUpdate();

    }

    public void removeFavourite(int userId, int cardId) throws SQLException {
        String sql = "delete from favourites where user_id=? and card_id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, cardId);
        preparedStatement.executeUpdate();
    }

    public List<Integer> getDamages(int plantId) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT damage_id from plant_damage_link where plant_id=" + plantId;
        ResultSet resultSet = statement.executeQuery(sql);
        List<Integer> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getInt("damage_id"));
        }
        return list;
    }
}
