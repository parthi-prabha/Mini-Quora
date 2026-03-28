package com.batman.spring.controller;

import com.batman.spring.dto.*;
import com.batman.spring.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest request) {
        try {
            PostResponse post = postService.createPost(request);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(
            @RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(postService.getAllPostsRanked(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(
            @PathVariable Long id,
            @RequestParam(required = false) Long userId) {
        try {
            return ResponseEntity.ok(postService.getPostById(id, userId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
