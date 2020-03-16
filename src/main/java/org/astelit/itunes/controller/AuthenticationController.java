package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.AuthenticationRequest;
import org.astelit.itunes.dto.AuthenticationToken;
import org.astelit.itunes.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationToken authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return userService.authenticate(request);
    }
}
