package ru.kpfu.itis.gimaletdinova.dto;

public class UserDto {
    private String name;
    private String lastname;
    private String img;
    private String login;

    public UserDto(String name, String lastname, String img, String login) {
        this.name = name;
        this.lastname = lastname;
        this.img = img;
        this.login = login;
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

    public String getLogin() {return login;}
}
