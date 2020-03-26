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

@Getter
@Setter
@NoArgsConstructor
public class UpdateSongRequest {
    @NotNull
    private Long id;

    @ConfirmString
    private String title;

    @NotNegative
    private int duration;

    private Long album;

    private Long playlist;
}
