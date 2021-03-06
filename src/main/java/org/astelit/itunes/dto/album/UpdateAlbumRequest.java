package org.astelit.itunes.dto.album;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.ConfirmString;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.entity.Song;

import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;

import javax.persistence.Id;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAlbumRequest {
    @NotNull
    private Long id;

    @ConfirmString
    private String title;

    private Date releaseDate;

    @ConfirmString
    private String genre;

    private Long artist;
}
