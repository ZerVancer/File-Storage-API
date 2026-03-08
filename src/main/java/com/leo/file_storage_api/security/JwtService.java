package com.leo.file_storage_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class JwtService {

  private static final Algorithm algorithm = Algorithm.HMAC256("frehqvirheiuoqhvruioewhuio");
  private static final JWTVerifier verifier = JWT.require(algorithm).withIssuer("file_storage_api").build();

  public String generateToken(UUID userID) {
    return JWT.create()
        .withIssuer("file_storage_api")
        .withIssuedAt(Instant.now())
        .withExpiresAt(Instant.now().plus(5, ChronoUnit.MINUTES))
        .withSubject(userID.toString())
        .sign(algorithm);
  }

  public UUID validateToken(String token) {
    DecodedJWT jwt = verifier.verify(token);
    return UUID.fromString(jwt.getSubject());
  }
}
