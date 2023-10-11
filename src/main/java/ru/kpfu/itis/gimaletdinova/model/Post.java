package ru.kpfu.itis.gimaletdinova.model;

import java.io.File;
import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
//    private String problem;
    private String text;
    private User author;
    private LocalDateTime dateTime;
    private File img;

    public Post(int id, String title, String text, User author, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.author = author;
        this.dateTime = dateTime;
    }
}
