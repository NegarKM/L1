package com.loyaltyone.interview.repository;

import com.loyaltyone.interview.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPostRepository extends PagingAndSortingRepository<Post, Long> {
}
