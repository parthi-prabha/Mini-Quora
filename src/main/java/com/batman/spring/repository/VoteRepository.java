package com.batman.spring.repository;

import com.batman.spring.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUserIdAndPostId(Long userId, Long postId);

    @Query("SELECT COALESCE(SUM(v.voteType), 0) FROM Vote v WHERE v.post.id = :postId")
    int getVoteScore(@Param("postId") Long postId);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.post.id = :postId AND v.voteType = 1")
    long countUpvotes(@Param("postId") Long postId);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.post.id = :postId AND v.voteType = -1")
    long countDownvotes(@Param("postId") Long postId);
}
