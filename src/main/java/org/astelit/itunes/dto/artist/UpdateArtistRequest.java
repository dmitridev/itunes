package org.astelit.itunes.dto.artist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.astelit.itunes.contstraint.ConfirmString;
import org.astelit.itunes.entity.Album;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UpdateArtistRequest {

    @Id
    private Long id;

    @ConfirmString
    private String name;

    private Long albumsList;

}
