package org.astelit.itunes.entity;


import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Table(name = "songs")
@Transactional
public class Song extends BaseEntity {

    @NotNull
    private String title;

    @NotNull
    private int duration;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToMany(fetch = EAGER, mappedBy = "songs")
    private List<Playlist> playlists;

    @Transactional
    public void addToPlaylists(Playlist playlist) {
        playlists.add(playlist);
    }
}
