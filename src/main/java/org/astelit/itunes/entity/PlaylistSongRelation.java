package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "relation_playlist_song")
@RequiredArgsConstructor
public class PlaylistSongRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "song_id",updatable = false)
    private Long songId;


    @Column(name = "playlist_id", updatable = false)
    private Long playlistId;
}
