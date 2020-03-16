package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraints.Cyrillic;
import org.astelit.itunes.contstraints.Login;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @Cyrillic
    private String name;

    @OneToMany(mappedBy = "author")
    private Set<Playlist> playlists = new HashSet<>();
}
