package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.PlaylistName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
@Getter
@Setter
public class Playlist extends BaseEntity {
    @PlaylistName
    private String name;

    @NotNull
    @JoinColumn(name = "id_author")
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @ManyToMany(mappedBy="playlists")
    Set<Song> songsList = new HashSet<>();
}
