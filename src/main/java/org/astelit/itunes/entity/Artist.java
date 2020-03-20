package org.astelit.itunes.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="artists")
public class Artist extends BaseEntity {

    private String name;


    @OneToMany(mappedBy="artist",fetch = FetchType.EAGER)
    private Set<Album> albumsList = new HashSet<>();
}
