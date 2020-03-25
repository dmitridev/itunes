package org.astelit.itunes.repository;


import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SongRepository  extends JpaRepository<Song,Long>, JpaSpecificationExecutor<Song> {

    Page<Song> findByTitleIsLikeOrderByTitleAsc(String name, Pageable pageable);
    List<Song> findByAlbum_Id(Long id);


}
