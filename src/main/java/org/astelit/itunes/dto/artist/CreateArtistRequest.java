package org.astelit.itunes.dto.artist;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.ConfirmString;
import org.astelit.itunes.entity.Album;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateArtistRequest {

    @ConfirmString
    private String name;

    private Long albumsList;
}
