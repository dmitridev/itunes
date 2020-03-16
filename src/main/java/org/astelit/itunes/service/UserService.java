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

}
