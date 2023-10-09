package ru.kpfu.itis.gimaletdinova.service;

import java.util.List;
import java.util.stream.Collectors;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dao.implementations.UserDao;
import ru.kpfu.itis.gimaletdinova.dto.UserDto;
import ru.kpfu.itis.gimaletdinova.model.User;
import ru.kpfu.itis.gimaletdinova.util.PasswordUtil;


public class UserServiceImplementation implements UserService {
    private final Dao<User> dao = new UserDao();

    @Override
    public List<UserDto> getAll() {
        return dao
                .getAll()
                .stream()
                .map(u -> new UserDto(u.getName(), u.getLastname()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto get(int id) {
        User u = dao.get(id);
        return new UserDto(u.getName(), u.getLastname());
    }

    @Override
    public void save(User user) {
        user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        dao.save(user);
    }
}
