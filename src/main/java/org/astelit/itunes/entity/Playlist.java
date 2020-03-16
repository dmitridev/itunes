package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraints.PlaylistName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playlists")
@Getter
@Setter
public class Playlist extends BaseEntity {
    @PlaylistName
    private String name;

    @NotNull
    @JoinColumn(name = "id_user")
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    // TODO: Tracks
}
