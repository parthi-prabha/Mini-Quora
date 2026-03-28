package com.batman.spring.repository;

import com.batman.spring.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p LEFT JOIN p.votes v GROUP BY p ORDER BY COALESCE(SUM(v.voteType), 0) DESC, p.createdAt DESC")
    List<Post> findAllRankedByVotes();
}
