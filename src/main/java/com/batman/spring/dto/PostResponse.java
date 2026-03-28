package com.batman.spring.dto;

import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private String title;
    private String body;
    private String authorName;
    private Long authorId;
    private LocalDateTime createdAt;
    private int voteScore;
    private long upvotes;
    private long downvotes;
    private long commentCount;
    private int userVote;

    public PostResponse() {}

    public PostResponse(Long id, String title, String body, String authorName, Long authorId,
                        LocalDateTime createdAt, int voteScore, long upvotes, long downvotes,
                        long commentCount, int userVote) {
        this.id = id; this.title = title; this.body = body;
        this.authorName = authorName; this.authorId = authorId;
        this.createdAt = createdAt; this.voteScore = voteScore;
        this.upvotes = upvotes; this.downvotes = downvotes;
        this.commentCount = commentCount; this.userVote = userVote;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public int getVoteScore() { return voteScore; }
    public void setVoteScore(int voteScore) { this.voteScore = voteScore; }
    public long getUpvotes() { return upvotes; }
    public void setUpvotes(long upvotes) { this.upvotes = upvotes; }
    public long getDownvotes() { return downvotes; }
    public void setDownvotes(long downvotes) { this.downvotes = downvotes; }
    public long getCommentCount() { return commentCount; }
    public void setCommentCount(long commentCount) { this.commentCount = commentCount; }
    public int getUserVote() { return userVote; }
    public void setUserVote(int userVote) { this.userVote = userVote; }
}
