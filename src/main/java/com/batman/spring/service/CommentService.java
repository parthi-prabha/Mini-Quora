package com.batman.spring.service;

import com.batman.spring.dto.*;
import com.batman.spring.entity.*;
import com.batman.spring.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public CommentResponse addComment(CreateCommentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setBody(request.getBody());
        comment.setUser(user);
        comment.setPost(post);

        comment = commentRepository.save(comment);
        return toResponse(comment);
    }

    public List<CommentResponse> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CommentResponse toResponse(Comment comment) {
        CommentResponse resp = new CommentResponse();
        resp.setId(comment.getId());
        resp.setBody(comment.getBody());
        resp.setAuthorName(comment.getUser().getName());
        resp.setAuthorId(comment.getUser().getId());
        resp.setCreatedAt(comment.getCreatedAt());
        return resp;
    }
}
