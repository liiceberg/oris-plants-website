package ru.kpfu.itis.gimaletdinova.dto;

public class CommentDto {
    private int id;
    private UserDto author;
    private String dateTime;
    private String text;
    private Integer feedbackCommentId;

    public CommentDto(int id, UserDto author, String dateTime, String text, Integer feedbackCommentId) {
        this.id = id;
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
        this.feedbackCommentId = feedbackCommentId;
    }

    public int getId() {
        return id;
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

    public Integer getFeedbackCommentId() {
        return feedbackCommentId;
    }
}
