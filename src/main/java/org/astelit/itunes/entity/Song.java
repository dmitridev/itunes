package org.astelit.itunes.entity;


import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="songs")
public class Song extends BaseEntity {

    @NotNull
    private String title;

    @NotNull
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="album_id")
    private Album album;

    @ManyToMany(mappedBy="songsList")
    private List<Playlist> playlists;
}
