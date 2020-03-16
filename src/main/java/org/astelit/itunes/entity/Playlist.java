package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.PlaylistName;

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
    @JoinColumn(name = "id_author")
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    // TODO: Tracks
}
