package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraints.Login;
import org.astelit.itunes.enums.Role;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "users")
@Entity
@NoArgsConstructor
public class User extends BaseEntity {
    @Login
    private String login;

    @OneToMany(mappedBy = "author")
    private Set<Playlist> playlists = new HashSet<>();

    public User(String login) {
        this.login = login;
    }
}
