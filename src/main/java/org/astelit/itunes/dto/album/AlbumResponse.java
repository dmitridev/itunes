package org.astelit.itunes.dto.album;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.ConfirmString;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.entity.Song;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
public class AlbumResponse extends EntityResponse {

    @ConfirmString
    private String title;

    private Date releaseDate;

    @ConfirmString
    private String genre;

    private Long artist;

    private Long songsList;

    public AlbumResponse(Album album){
        super(album);
        this.title = album.getTitle();
        this.releaseDate = album.getReleaseDate();
        this.genre = album.getGenre();
    }
}
