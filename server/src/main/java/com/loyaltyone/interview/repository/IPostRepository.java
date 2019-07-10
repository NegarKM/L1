package com.loyaltyone.interview.repository;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPostRepository extends PagingAndSortingRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId order by p.timestamp desc")
    List<Post> fetchPostsByUser(@Param("userId") Long userId);
}
