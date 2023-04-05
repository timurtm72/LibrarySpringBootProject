package ru.maxima.libraryspringbootproject.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component // Marks this as a component. Now, Spring Boot will handle the creation and management of the JWTUtil Bean
// and you will be able to inject it in other places of your code
public class JWTUtil {

    // Injects the jwt-secret property set in the resources/application.properties file
    @Value("${jwt-secret}")
    private String secret;

    // Method to sign and create a JWT using the injected secret
    public String generateToken(String name) throws IllegalArgumentException, JWTCreationException {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(30).toInstant());
        return JWT.create()
                .withSubject("User Details")
                .withClaim("name", name)
                .withIssuedAt(new Date())
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("name").asString();
    }

}
