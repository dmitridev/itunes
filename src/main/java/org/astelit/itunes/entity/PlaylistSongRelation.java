package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "relation_playlist_song")
@RequiredArgsConstructor
public class PlaylistSongRelation implements Serializable {

    @Column(name = "song_id",unique = false, insertable = true, updatable = false)
    private Long songId;

    @Id
    @Column(name = "playlist_id",unique = false, insertable = true, updatable = false)
    private Long playlistId;
}
