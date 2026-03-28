package com.batman.spring.service;

import com.batman.spring.dto.*;
import com.batman.spring.entity.*;
import com.batman.spring.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository,
                       VoteRepository voteRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
        this.commentRepository = commentRepository;
    }

    public PostResponse createPost(CreatePostRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setUser(user);

        post = postRepository.save(post);
        return toResponse(post, null);
    }

    public List<PostResponse> getAllPostsRanked(Long currentUserId) {
        List<Post> posts = postRepository.findAllRankedByVotes();
        return posts.stream()
                .map(post -> toResponse(post, currentUserId))
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id, Long currentUserId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return toResponse(post, currentUserId);
    }

    private PostResponse toResponse(Post post, Long currentUserId) {
        int voteScore = voteRepository.getVoteScore(post.getId());
        long upvotes = voteRepository.countUpvotes(post.getId());
        long downvotes = voteRepository.countDownvotes(post.getId());
        long commentCount = commentRepository.countByPostId(post.getId());

        int userVote = 0;
        if (currentUserId != null) {
            userVote = voteRepository.findByUserIdAndPostId(currentUserId, post.getId())
                    .map(Vote::getVoteType)
                    .orElse(0);
        }

        PostResponse resp = new PostResponse();
        resp.setId(post.getId());
        resp.setTitle(post.getTitle());
        resp.setBody(post.getBody());
        resp.setAuthorName(post.getUser().getName());
        resp.setAuthorId(post.getUser().getId());
        resp.setCreatedAt(post.getCreatedAt());
        resp.setVoteScore(voteScore);
        resp.setUpvotes(upvotes);
        resp.setDownvotes(downvotes);
        resp.setCommentCount(commentCount);
        resp.setUserVote(userVote);
        return resp;
    }
}
