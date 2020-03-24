package org.astelit.itunes.dto.playlist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.PlaylistName;
import org.astelit.itunes.entity.Song;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlaylistUpdateRequest {
    @NotNull
    private Long id;

    @PlaylistName
    private String name;

    private Long songList;
}
