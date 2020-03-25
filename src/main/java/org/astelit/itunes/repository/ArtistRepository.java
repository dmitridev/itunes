package org.astelit.itunes.repository;

import org.astelit.itunes.dto.artist.ArtistResponse;
import org.astelit.itunes.dto.artist.ArtistSearchRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.hibernate.Criteria;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {

    Page<Artist> findByNameIsLikeOrderByNameAsc(String name, Pageable pageable);


    default Page<Artist> search(ArtistSearchRequest request){
        return findAll((Specification<Artist>) (root,query,cb) ->{
            List<Predicate> predicateList = new ArrayList<Predicate>();

            if(request.getName() != null) {
                predicateList.add(cb.equal(root.get("artist").get("name"), request.getName()));
                predicateList.add(cb.like(root.get("artist").get("name"),request.getLikeName()));
            }

            if(request.getGenre() != null) {
                predicateList.add(cb.equal(root.get("artist").get("album").get("genre"), request.getGenre()));
                predicateList.add(cb.like(root.get("artist").get("name"),request.getLikeName()));
            }

            return cb.or(predicateList.toArray(new Predicate[0]));
        },request.pageable());
    }
}
