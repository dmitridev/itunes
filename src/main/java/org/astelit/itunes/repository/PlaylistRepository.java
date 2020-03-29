package org.astelit.itunes.repository;

import org.astelit.itunes.dto.playlist.PlaylistSearchRequest;
import org.astelit.itunes.entity.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>, JpaSpecificationExecutor<Playlist> {

    default Page<Playlist> search(PlaylistSearchRequest request) {
        return findAll((Specification<Playlist>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getArtistId() != null) {
                predicates.add(cb.equal(root.join("songs").get("album").get("artist").get("id"), request.getArtistId()));
            }

            if (request.getAlbumId() != null) {
                predicates.add(cb.equal(root.join("songs").get("album").get("id"), request.getAlbumId()));
            }

            if (request.getSongId() != null) {
                predicates.add(cb.equal(root.join("songs").get("id"), request.getSongId()));
            }


            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }


}
