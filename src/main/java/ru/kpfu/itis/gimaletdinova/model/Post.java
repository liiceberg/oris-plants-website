package ru.kpfu.itis.gimaletdinova.model;

import java.io.File;
import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private String problem;
    private String text;
    private User author;
    private LocalDateTime dateTime;
    private File img;
}
