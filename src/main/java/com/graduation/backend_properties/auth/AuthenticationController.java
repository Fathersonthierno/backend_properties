package com.graduation.backend_properties.auth ;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduation.backend_properties.modele.User;
import com.graduation.backend_properties.repository.UserRepository;
import com.graduation.backend_properties.utils.MyGenerator;
import com.graduation.backend_properties.utils.RefreshTokenDTO;
import com.graduation.backend_properties.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api/${api-version}/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final UserDetailsService userDetailsService;

  @PostMapping("/register")
  public ResponseEntity<com.graduation.backend_properties.auth.AuthenticationResponse> register(
      @RequestBody RegisterRequest request, MyGenerator generator
  ) {
    return ResponseEntity.ok(service.register(request, generator));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<?> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    Optional<User> userByEmail = userRepository.findByEmail(request.getEmail());
    if (userByEmail == null) return ResponseEntity.badRequest().build();
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userByEmail.get().getEmail(), request.getPassword());
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    User existUser = (User)authentication.getPrincipal();
    org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
            existUser.getUsername(), existUser.getPassword(), existUser.getAuthorities()
    );
    Map<String, String> tokens = TokenUtils.generateTokensMap(user, "PROPERTY APP");
    return ResponseEntity.ok(tokens);
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDTO refreshToken, HttpServletRequest request, HttpServletResponse response) throws IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        String old_token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("PropertyApplicationSecret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(old_token);
        String email = decodedJWT.getSubject();
        UserDetails user = userDetailsService.loadUserByUsername(email);
        Map<String, String> tokens = new HashMap<>();
        if (user != null && decodedJWT.getExpiresAt().before(new Date())) {
          tokens = TokenUtils.generateTokensMap(user, "PROPERTY APP");
          response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        } else {
//                    LOGGER.info("TOKEN NOT EXPIRED");
          tokens.put("access_token", old_token);
          tokens.put("refresh_token", refreshToken.getToken());
        }
        return ResponseEntity.ok(tokens);
      } catch (Exception exception) {
        response.setHeader("error", exception.getMessage());
        response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error_message", exception.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
      }
    } else {
      throw new RuntimeException("Refresh token is missing");
    }
    return ResponseEntity.badRequest().build();
  }
}
