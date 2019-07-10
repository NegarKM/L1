package com.loyaltyone.interview.config;

import com.loyaltyone.interview.model.User;
import com.loyaltyone.interview.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private IUserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("loading user with username: " + username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("user not found.");
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }

    private class MyUserPrincipal implements UserDetails {
        private final User user;

        MyUserPrincipal(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return new BCryptPasswordEncoder(11).encode(user.getPassword());
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}