package com.milton.activoscz.Graphql.Security.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.milton.activoscz.Graphql.Security.TokenPayload;
import com.milton.activoscz.Graphql.Security.TokenUtils;
import com.milton.activoscz.Model.User;

import org.springframework.stereotype.Component;

@Component
public class JWTTokenUtils extends TokenUtils {
    public TokenPayload decodeToken(String authorizationHeader) {
        DecodedJWT decodedToken = JWT.require(Algorithm.HMAC512(getSecret().getBytes()))
                .build()
                .verify(authorizationHeader.replace(getTokenPrefix(), ""));

        return new TokenPayload(decodedToken.getSubject(), decodedToken.getClaim("role").as(User.Role.class));
    }
}