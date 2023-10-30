package ru.kpfu.itis.gimaletdinova.model;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private int authorId;
    private LocalDateTime dateTime;
    private String text;
    private int postId;
    private Integer feedbackCommentId;

    public Comment(int id, int authorId, LocalDateTime dateTime, String text, int postId, Integer feedbackCommentId) {
        this.id = id;
        this.authorId = authorId;
        this.dateTime = dateTime;
        this.text = text;
        this.postId = postId;
        this.feedbackCommentId = feedbackCommentId;
    }

    public Comment(int authorId, String text, int postId, Integer feedbackCommentId) {
        this.authorId = authorId;
        this.text = text;
        this.postId = postId;
        this.feedbackCommentId = feedbackCommentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Integer getFeedbackCommentId() {
        return feedbackCommentId;
    }

    public void setFeedbackCommentId(int feedbackCommentId) {
        this.feedbackCommentId = feedbackCommentId;
    }
}
