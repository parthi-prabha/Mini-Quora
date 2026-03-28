package com.batman.spring.service;

import com.batman.spring.dto.*;
import com.batman.spring.entity.*;
import com.batman.spring.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository,
                       PostRepository postRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public String castVote(VoteRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<Vote> existingVote = voteRepository.findByUserIdAndPostId(
                request.getUserId(), request.getPostId());

        if (existingVote.isPresent()) {
            Vote vote = existingVote.get();
            if (vote.getVoteType() == request.getVoteType()) {
                // Same vote type → remove vote (toggle off)
                voteRepository.delete(vote);
                return "Vote removed";
            } else {
                // Different vote type → update
                vote.setVoteType(request.getVoteType());
                voteRepository.save(vote);
                return "Vote updated";
            }
        }

        // New vote
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setPost(post);
        vote.setVoteType(request.getVoteType());
        voteRepository.save(vote);
        return "Vote cast";
    }

    @Transactional
    public void removeVote(Long userId, Long postId) {
        Vote vote = voteRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(() -> new RuntimeException("Vote not found"));
        voteRepository.delete(vote);
    }
}
