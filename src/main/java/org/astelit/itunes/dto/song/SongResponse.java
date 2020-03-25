package org.astelit.itunes.dto.song;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.ConfirmString;
import org.astelit.itunes.contstraint.NotNegative;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.Song;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Setter
@Getter
public class SongResponse extends EntityResponse {

    @ConfirmString
    private String title;

    @NotNegative
    private int duration;

    private Long album;

    public SongResponse(Song song){
        super(song);
        this.title = song.getTitle();
        this.duration = song.getDuration();
        this.album = song.getAlbum().getId();
    }


}
