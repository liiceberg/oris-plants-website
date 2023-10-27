package ru.kpfu.itis.gimaletdinova.service;

import ru.kpfu.itis.gimaletdinova.dto.DamageDto;

import java.util.List;

public interface DamageService {
    List<DamageDto> getAll();
    DamageDto get(int id);
}
