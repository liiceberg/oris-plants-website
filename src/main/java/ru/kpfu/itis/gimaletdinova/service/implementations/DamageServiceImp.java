package ru.kpfu.itis.gimaletdinova.service.implementations;

import ru.kpfu.itis.gimaletdinova.dao.Dao;
import ru.kpfu.itis.gimaletdinova.dto.DamageDto;
import ru.kpfu.itis.gimaletdinova.dto.PostDto;
import ru.kpfu.itis.gimaletdinova.model.Damage;
import ru.kpfu.itis.gimaletdinova.model.Post;
import ru.kpfu.itis.gimaletdinova.model.enam.IllegalEnumValueException;
import ru.kpfu.itis.gimaletdinova.service.DamageService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DamageServiceImp implements DamageService {
    private final Dao<Damage> damageDao;

    public DamageServiceImp(Dao<Damage> damageDao) {
        this.damageDao = damageDao;
    }

    @Override
    public List<DamageDto> getAll() {
        try {
            return damageDao
                    .getAll()
                    .stream()
                    .map(d -> new DamageDto(d.getId(), d.getCausativeAgentDescription()))
                    .collect(Collectors.toList());
        } catch (SQLException | IllegalEnumValueException e) {
            return null;
        }
    }

    @Override
    public DamageDto get(int id) {
        try {
            Damage d = damageDao.get(id);
            return new DamageDto(d.getId(), d.getCausativeAgentDescription());
        }
        catch (SQLException | IllegalEnumValueException e) {
            return null;
        }
    }
}
