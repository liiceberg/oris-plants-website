package ru.kpfu.itis.gimaletdinova.model;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private User author;
    private LocalDateTime dateTime;
    private String text;
    private Post post;
    private User feedbackUser;
}
