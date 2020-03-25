package org.astelit.itunes.entity;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="artists")
public class Artist extends BaseEntity {

    private String name;


    @OneToMany(mappedBy="artist",fetch = FetchType.LAZY)
    private Set<Album> albumsList = new HashSet<>();




}
