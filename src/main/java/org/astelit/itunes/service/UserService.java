package org.astelit.itunes.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.astelit.itunes.configuration.AuthenticationProperties;
import org.astelit.itunes.dto.AuthenticationRequest;
import org.astelit.itunes.dto.AuthenticationToken;
import org.astelit.itunes.exception.BadRequestException;
import org.astelit.itunes.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final AuthenticationProperties properties;

    public AuthenticationToken authenticate(AuthenticationRequest request) {
        String digest = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        boolean exists = repository.existsByLoginAndPassword(request.getLogin(), digest);

        if (!exists)
            throw new BadRequestException("Invalid login or password");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 14);
        Date expiration = calendar.getTime();

        Claims claims = new DefaultClaims();
        claims.setExpiration(expiration);
        claims.setSubject(request.getLogin());

        String token = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.ES512, properties.getSalt())
                .compact();

        return new AuthenticationToken(token, expiration.toInstant().getEpochSecond(), claims);
    }
}
