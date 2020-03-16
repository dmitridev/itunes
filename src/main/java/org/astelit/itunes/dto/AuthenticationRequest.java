package org.astelit.itunes.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthenticationRequest {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
