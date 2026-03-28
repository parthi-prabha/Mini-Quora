package com.batman.spring.controller;

import com.batman.spring.dto.VoteRequest;
import com.batman.spring.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<?> castVote(@RequestBody VoteRequest request) {
        try {
            String result = voteService.castVote(request);
            return ResponseEntity.ok(Map.of("message", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> removeVote(
            @RequestParam Long userId,
            @RequestParam Long postId) {
        try {
            voteService.removeVote(userId, postId);
            return ResponseEntity.ok(Map.of("message", "Vote removed"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
