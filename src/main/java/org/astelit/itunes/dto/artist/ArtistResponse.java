package org.astelit.itunes.dto.artist;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.ConfirmString;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;


import java.util.Set;


@Getter
@Setter
public class ArtistResponse extends EntityResponse {

    @ConfirmString
    private String name;

    private Set<Album> albumsList;

    public ArtistResponse(Artist artist){
        super(artist);
        this.name = artist.getName();
        this.albumsList = artist.getAlbumsList();
    }
}
