package com.batman.spring.dto;

public class CreateCommentRequest {
    private Long userId;
    private Long postId;
    private String body;

    public CreateCommentRequest() {}
    public CreateCommentRequest(Long userId, Long postId, String body) { this.userId = userId; this.postId = postId; this.body = body; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
