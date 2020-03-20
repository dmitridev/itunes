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
public class PlaylistCreateRequest {
    @PlaylistName
    private String name;

    @NotNull
    private Long author;

    private Set<Song> songList;
}
