package org.astelit.itunes.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraints.Login;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {
    @NotNull
    private Long id;

    @Login
    private String login;
}
