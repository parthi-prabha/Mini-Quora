package com.batman.spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "votes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "post_id"})
})
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vote_type", nullable = false)
    private int voteType; // 1 for upvote, -1 for downvote

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Vote() {}

    public Vote(Long id, int voteType, User user, Post post) {
        this.id = id;
        this.voteType = voteType;
        this.user = user;
        this.post = post;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getVoteType() { return voteType; }
    public void setVoteType(int voteType) { this.voteType = voteType; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
}
