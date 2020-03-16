package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.enums.Role;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Length(min = 4, max = 64)
    @Pattern(regexp = "^[a-z0-9]*$", message = "cодержит недопустимые символы")
    String login;

    @NotEmpty
    String password;

    @Enumerated(EnumType.STRING)
    Role role;
}
