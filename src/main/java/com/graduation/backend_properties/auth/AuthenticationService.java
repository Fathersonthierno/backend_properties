package com.graduation.backend_properties.auth ;

import com.graduation.backend_properties.modele.PropertyOwner;
import com.graduation.backend_properties.modele.Role;
import com.graduation.backend_properties.modele.User;
import com.graduation.backend_properties.repository.PropertyownerRepository;
import com.graduation.backend_properties.repository.UserRepository;
import com.graduation.backend_properties.utils.GeneratorType;
import com.graduation.backend_properties.utils.MyGenerator;
import com.graduation.backend_properties.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthenticationService implements UserDetailsService {
  private final UserRepository repository;
  private final PropertyownerRepository propertyownerRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  public com.graduation.backend_properties.auth.AuthenticationResponse register(RegisterRequest request, MyGenerator generator) {
    var user = User.builder()
        .prenom(request.getFirstname())
        .nom(request.getLastname())
        .email(request.getEmail())
        .username(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getTypeUtilisateur().equals("Annonceur")? Role.ANNONCEUR : Role.ACHETEUR)
        .build();

    var savedUser = repository.save(user);

    if (request.getTypeUtilisateur().equals("Annonceur")){
      PropertyOwner propertyOwner = PropertyOwner.builder()
              .code(generator.generateAlphaNumericStringFromData(4, GeneratorType.NUMERIC))
              .nbbien(0)
              .user(user)
              .build();
      propertyownerRepository.save(propertyOwner);
    }
    Map<String, String> tokens = TokenUtils.generateTokensMap(user, "PROPERTY APP");
    return com.graduation.backend_properties.auth.AuthenticationResponse.builder()
            .accessToken(tokens.get("access_token"))
            .refreshToken(tokens.get("refresh_token"))
            .build();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOptional = userRepository.findByEmail(username);
    if (userOptional.isEmpty()) {
      throw new UsernameNotFoundException("User not found in the database");
    }
      User user = userOptional.get();
      Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(user.getRole().getAuthorities());
    return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            authorities
    );
  }
}
