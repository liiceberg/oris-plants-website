package ru.kpfu.itis.gimaletdinova.dto;

public class UserDto {
    private String name;
    private String lastname;

    public UserDto(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return name + " " + lastname;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }
}
