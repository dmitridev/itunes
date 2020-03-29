package org.astelit.itunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.astelit.itunes.entity.PlaylistSongRelation;

import java.util.List;

public interface RelationsRepository extends JpaRepository<PlaylistSongRelation, Long> {
    List<PlaylistSongRelation> findBySongId(Long id);
    List<PlaylistSongRelation> findByPlaylistId(Long id);
}
