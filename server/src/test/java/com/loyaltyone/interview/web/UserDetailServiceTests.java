package com.loyaltyone.interview.web;

import com.loyaltyone.interview.config.MyUserDetailsService;
import com.loyaltyone.interview.model.User;
import com.loyaltyone.interview.repository.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailServiceTests {
    @Mock
    IUserRepository userRepoMock;

    @InjectMocks
    MyUserDetailsService userDetailsService;

    @Test
    public void loadUserByUsername_successful() {
        String username = "test";
        String password = "123";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepoMock.findByUsername(username))
                .thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());

        BCryptPasswordEncoder cd = new BCryptPasswordEncoder(11);
        assertTrue(cd.matches(password, userDetails.getPassword()));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_unsuccessful() {
        String invalidUsername = "invalidTest";

        when(userRepoMock.findByUsername(invalidUsername))
                .thenReturn(null);

        userDetailsService.loadUserByUsername(invalidUsername);
    }
}
