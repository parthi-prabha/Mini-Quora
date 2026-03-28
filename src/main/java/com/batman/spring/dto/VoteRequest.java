package com.batman.spring.dto;

public class VoteRequest {
    private Long userId;
    private Long postId;
    private int voteType; // 1 for upvote, -1 for downvote

    public VoteRequest() {}
    public VoteRequest(Long userId, Long postId, int voteType) { this.userId = userId; this.postId = postId; this.voteType = voteType; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }
    public int getVoteType() { return voteType; }
    public void setVoteType(int voteType) { this.voteType = voteType; }
}
