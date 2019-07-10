package com.loyaltyone.interview.repository;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPostRepository extends PagingAndSortingRepository<Post, Long> {
    List<Post> findByUser(User user);
}
