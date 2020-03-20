package org.astelit.itunes.repository;

import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album,Long> {

    Page<Album> findByTitleIsLikeOrderByTitleAsc(String name, Pageable pageable);

}
