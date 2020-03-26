package org.astelit.itunes.dto.album;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.dto.SearchRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Getter
@Setter
public class AlbumSearchRequest {
    private String title;

    private String genre;

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
