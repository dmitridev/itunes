package org.astelit.itunes.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraints.Login;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {
    @Login
    private String login;
}
