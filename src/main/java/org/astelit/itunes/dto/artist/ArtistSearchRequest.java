package org.astelit.itunes.dto.artist;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Getter
@Setter
public class ArtistSearchRequest {
    private Integer page = 0;
    private Integer size = 10;
    private String name;
    private String genre;

    public Pageable pageable(){return PageRequest.of(page,size);}

    public String getLikeName(){return "%"+ name.toUpperCase() + "%";}
    public String getLikeGenre(){return "%" + genre.toUpperCase() + "%";}
}
