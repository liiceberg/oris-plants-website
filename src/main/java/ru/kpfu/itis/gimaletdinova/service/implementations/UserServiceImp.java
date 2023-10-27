package ru.kpfu.itis.gimaletdinova.service.implementations;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.dto.UserDto;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;
import ru.kpfu.itis.gimaletdinova.service.UserService;
import ru.kpfu.itis.gimaletdinova.util.PasswordUtil;


public class UserServiceImp implements UserService {
    private final Dao<User> dao;

    public UserServiceImp(Dao<User> dao) {
        this.dao = dao;
    }

    @Override
    public List<UserDto> getAll() {
        try {
            return dao
                    .getAll()
                    .stream()
                    .map(u -> new UserDto(u.getName(), u.getLastname(), u.getImg(), u.getLogin()))
                    .collect(Collectors.toList());
        } catch (SQLException | IllegalEnumValueException e) {
            return null;
        }
    }

    @Override
    public UserDto get(int id) {
        try {
            User u = dao.get(id);
            return new UserDto(u.getName(), u.getLastname(), u.getImg(), u.getLogin());
        } catch (SQLException | IllegalEnumValueException e) {
            return null;
        }
    }

    @Override
    public boolean save(User user) {
        try {
            user.setPassword(PasswordUtil.encrypt(user.getPassword()));
            dao.save(user);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
