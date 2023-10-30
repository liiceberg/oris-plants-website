package ru.kpfu.itis.gimaletdinova.dto;

public class UserDto {
    private int id;
    private String name;
    private String lastname;
    private String img;
    private String login;
    private String description;

    public UserDto(int id, String name, String lastname, String img, String login, String description) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.img = img;
        this.login = login;
        this.description = description;
    }

    @Override
    public String toString() {
        return name + " " + lastname;
    }

    public int getId() {
        return id;
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

    public String getLogin() {return login;}

    public String getDescription() {
        return description;
    }
}
