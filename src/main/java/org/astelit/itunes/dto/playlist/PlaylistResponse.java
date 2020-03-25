package org.astelit.itunes.dto.playlist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.Song;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class PlaylistResponse extends EntityResponse {
    private final String name;

    public PlaylistResponse(Playlist playlist) {
        super(playlist);
        this.name = playlist.getName();
    }
}
