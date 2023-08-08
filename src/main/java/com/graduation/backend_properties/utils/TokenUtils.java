package com.graduation.backend_properties.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenUtils {

    public static Map<String, String> generateTokensMap(UserDetails user, String issuer) {
        Algorithm algorithm = Algorithm.HMAC256("PropertyApplicationSecret".getBytes());
        String access_token  = JWT.create()
                .withSubject(user.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000))
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000))  // 5 jours
                .withIssuer(issuer)
                .withClaim("roles", user.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token  = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 20 * 24 * 60 * 60 * 1000))   // 20 jours
                .withIssuer("PROPERTY APP")
                .sign(algorithm);

       /* response.setHeader("access_token", access_token);
        response.setHeader("refresh_token", refresh_token);*/
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        return tokens;
    }
}
