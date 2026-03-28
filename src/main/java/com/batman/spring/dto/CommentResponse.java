package com.batman.spring.dto;

import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private String body;
    private String authorName;
    private Long authorId;
    private LocalDateTime createdAt;

    public CommentResponse() {}

    public CommentResponse(Long id, String body, String authorName, Long authorId, LocalDateTime createdAt) {
        this.id = id; this.body = body; this.authorName = authorName;
        this.authorId = authorId; this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
