package com.loyaltyone.interview.repository;

import com.loyaltyone.interview.model.Post;
import com.loyaltyone.interview.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
