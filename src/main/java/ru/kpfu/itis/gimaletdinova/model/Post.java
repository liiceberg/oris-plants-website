package ru.kpfu.itis.gimaletdinova.model;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private String text;
    private String img;
    private int authorId;
    private LocalDateTime dateTime;


    public Post(int id, String title, String text, String img, int authorId, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.img = img;
        this.authorId = authorId;
        this.dateTime = dateTime;
    }

    public Post(String title, String text, String img, int authorId) {
        this.title = title;
        this.text = text;
        this.img = img;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
