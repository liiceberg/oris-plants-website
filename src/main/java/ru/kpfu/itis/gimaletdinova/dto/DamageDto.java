package ru.kpfu.itis.gimaletdinova.dto;

public class DamageDto {
    private int id;
    private String name;

    public DamageDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
