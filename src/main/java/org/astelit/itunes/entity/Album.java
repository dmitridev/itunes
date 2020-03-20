package org.astelit.itunes.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.ConfirmString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="albums")
@Getter
@Setter
public class Album extends BaseEntity{

    @ConfirmString
    private String title;


    private Date releaseDate;

    @NotNull
    private String genre;


    @JoinColumn(name="artist_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Artist artist;


    @OneToMany(mappedBy="album",fetch=FetchType.EAGER)
    private Set<Song> songsList = new HashSet<>();

}
