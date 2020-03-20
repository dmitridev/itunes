package org.astelit.itunes.repository;

import org.astelit.itunes.dto.artist.ArtistResponse;
import org.astelit.itunes.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArtistRepository extends JpaRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {

    Page<Artist> findByNameIsLikeOrderByNameAsc(String name, Pageable pageable);

}
