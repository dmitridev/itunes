package org.astelit.itunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.astelit.itunes.entity.PlaylistSongRelation;

public interface RelationsRepository extends JpaRepository<PlaylistSongRelation, Long> {
}
