package org.astelit.itunes.entity;


import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name="songs")
public class Song extends BaseEntity {

    @Cyrillic
    private String title;

    @NotNull
    private int duration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="album_id")
    private Album album;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="playlist_id")
    private Playlist playlist;
}