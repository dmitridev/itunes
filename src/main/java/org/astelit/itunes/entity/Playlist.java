package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.PlaylistName;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlists")
@Getter
@Setter
@Transactional

public class Playlist extends BaseEntity {
    @PlaylistName
    private String name;

    @NotNull
    @JoinColumn(name = "id_author")
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "relation_playlist_song",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private Set<Song> songs = new HashSet<Song>();


    public void AddToSongs(Song song) {
        songs.add(song);
    }


}
