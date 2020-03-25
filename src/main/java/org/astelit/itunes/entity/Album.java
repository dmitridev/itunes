package org.astelit.itunes.entity;

import javax.annotation.ManagedBean;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.ConfirmString;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="albums")
@Getter
@Setter
@ManagedBean
public class Album extends BaseEntity{


    @ConfirmString
    private String title;


    private Date releaseDate;

    @NotNull
    private String genre;


    @JoinColumn(name="artist_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Artist artist;


    @OneToMany(mappedBy="album",fetch=FetchType.LAZY)
    private Set<Song> songsList = new HashSet<>();

}
