package ru.kpfu.itis.gimaletdinova.dto;


import java.time.LocalDateTime;

public class PostDto {
    private String title;
    private String text;
    private String img;
    private UserDto author;
    private LocalDateTime dateTime;

    public PostDto(String title, String text, String img, UserDto author, LocalDateTime dateTime) {
        this.title = title;
        this.text = text;
        this.img = img;
        this.author = author;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getImg() {
        return img;
    }

    public UserDto getAuthor() {
        return author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
