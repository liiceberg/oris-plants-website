package ru.kpfu.itis.gimaletdinova.dto;

public class UserDto {
    private String name;
    private String lastname;
    private String img;

    public UserDto(String name, String lastname, String img) {
        this.name = name;
        this.lastname = lastname;
        this.img = img;
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

    public String getImg() {
        return img;
    }
}
