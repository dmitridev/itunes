package org.astelit.itunes.repository;


import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.dto.song.SongSearchRequest;
import org.astelit.itunes.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public interface SongRepository extends JpaRepository<Song, Long>, JpaSpecificationExecutor<Song> {

    List<Song> findByAlbum_Id(Long id);

    default Page<Song> search(SongSearchRequest request) {
        return findAll((Specification<Song>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getTitle() != null) {
                predicates.add(cb.like(root.get("title"), request.getTitle()));
            }

            if (request.getGenre() != null) {
                predicates.add(cb.like(root.get("album").get("genre"), request.getGenre()));
            }

            if (request.getAlbumId() != null) {
                predicates.add(cb.equal(root.get("album").get("id"), request.getAlbumId()));
            }

            if (request.getArtistId() != null) {
                predicates.add(cb.equal(root.get("album").get("artist").get("id"), request.getArtistId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }


}
