package ru.kpfu.itis.gimaletdinova.dto;

public class PostDto {
    private int id;
    private String title;
    private String text;
    private String img;
    private UserDto author;
    private String dateTime;

    public PostDto(int id, String title, String text, String img, UserDto author, String dateTime) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.img = img;
        this.author = author;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
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

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
