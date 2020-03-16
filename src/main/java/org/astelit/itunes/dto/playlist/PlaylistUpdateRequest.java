package org.astelit.itunes.dto.playlist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraints.PlaylistName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PlaylistUpdateRequest {
    @NotNull
    private Long id;

    @PlaylistName
    private String name;

    // TODO: tracks
}
