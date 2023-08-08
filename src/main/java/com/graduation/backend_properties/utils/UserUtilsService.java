package com.graduation.backend_properties.utils;

import com.graduation.backend_properties.modele.User;
import com.graduation.backend_properties.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class UserUtilsService {
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        return null;
    }

    public static String getAuthenticatedUserUsername() {
        return Objects.requireNonNull(getAuthentication()).getName();
    }

    public static User getAuthenticatedUser(UserRepository userRepository) {
        String email = Objects.requireNonNull(getAuthentication()).getName();
        if (email == null) {
            return null;
        }
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);
    }
}
