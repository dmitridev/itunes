package org.astelit.itunes.auth.authorization;

import io.jsonwebtoken.Claims;
import lombok.Getter;

@Getter
public class AuthorizationToken {
    private final long issued;
    private final long expiration;
    private final String token;
    private final Claims claims;

    public AuthorizationToken(Claims claims, String token) {
        this.issued = claims.getIssuedAt().getTime();
        this.expiration = claims.getExpiration().getTime();
        this.token = token;
        this.claims = claims;
    }
}
