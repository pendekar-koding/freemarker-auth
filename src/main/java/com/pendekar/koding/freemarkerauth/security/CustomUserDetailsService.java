package com.pendekar.koding.freemarkerauth.security;

import com.pendekar.koding.freemarkerauth.entity.UserProfile;
import com.pendekar.koding.freemarkerauth.repository.UserProfileRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserProfileRepository userProfileRepository;

    public CustomUserDetailsService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile user = userProfileRepository.findByDeletedIsFalseAndUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getCode())
                .disabled(!user.getActive())
                .build();
    }
}
