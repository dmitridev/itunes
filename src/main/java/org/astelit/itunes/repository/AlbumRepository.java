package org.astelit.itunes.repository;

import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;


public interface AlbumRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {


    default Page<Album> search(AlbumSearchRequest request) {
        return findAll((Specification<Album>) (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();


            if (request.getTitle() != null) {
                predicateList.add(cb.like(root.get("title"), request.getTitle()));

            }

            if (request.getGenre() != null) {
                predicateList.add(cb.like(root.get("genre"), request.getGenre()));
                query.orderBy(cb.desc(root.get("updatedAt")));

            }

            if(request.getArtistId() != null){
                predicateList.add(cb.equal(root.get("artist").get("id"),request.getArtistId()));
            }


            return cb.and(predicateList.toArray(new Predicate[0]));
        }, request.pageable());
    }
}
