package org.astelit.itunes.dto.playlist;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.dto.SearchRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class PlaylistSearchRequest {
    private Long artistId;

    private Long albumId;

    private Long songId;

    private Integer page = 0;
    private Integer size = 10;

    public Pageable pageable() {
        return PageRequest.of(page, size);
    }

}
