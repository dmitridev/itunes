package org.astelit.itunes.repository;

import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SongRepository  extends JpaRepository<Song,Long> {

    Page<Song> findByTitleIsLikeOrderByTitleAsc(String name, Pageable pageable);

}
