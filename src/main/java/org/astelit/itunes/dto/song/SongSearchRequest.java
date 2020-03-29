package org.astelit.itunes.dto.song;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
public class SongSearchRequest {
    private String title;
    private String genre;
    private Long artistId;
    private Long albumId;
    private Integer page = 0;
    private Integer size = 10;

    public Pageable pageable() {
        return PageRequest.of(page, size);
    }

    public String getLikeTitle() {
        return "%" + title.toUpperCase() + "%";
    }

    public String getLikeGenre() {
        return "%" + genre.toUpperCase() + "%";
    }

}
