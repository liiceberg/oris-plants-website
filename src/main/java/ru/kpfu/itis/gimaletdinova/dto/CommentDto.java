package ru.kpfu.itis.gimaletdinova.dto;

public class CommentDto {
    private UserDto author;
    private String dateTime;
    private String text;
    private PostDto post;
    private UserDto feedbackUser;

    public CommentDto(UserDto author, String dateTime, String text, PostDto post, UserDto feedbackUser) {
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
        this.post = post;
        this.feedbackUser = feedbackUser;
    }

    public CommentDto(UserDto author, String dateTime, String text, PostDto post) {
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
        this.post = post;
    }

    public UserDto getAuthor() {
        return author;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getText() {
        return text;
    }

    public PostDto getPost() {
        return post;
    }

    public UserDto getFeedbackUser() {
        return feedbackUser;
    }
}
