package org.astelit.itunes.dto;

import io.jsonwebtoken.Claims;
import lombok.Getter;

@Getter
public class AuthenticationToken {
    private final String token;
    private final long expiration;
    private final Claims claims;

    public AuthenticationToken(String token, long expiration, Claims claims) {
        this.token = token;
        this.expiration = expiration;
        this.claims = claims;
    }
}
