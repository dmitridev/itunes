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
import javax.persistence.criteria.Predicate;


public interface SongRepository  extends JpaRepository<Song,Long>, JpaSpecificationExecutor<Song> {

    Page<Song> findByTitleIsLikeOrderByTitleAsc(String name, Pageable pageable);
    List<Song> findByAlbum_Id(Long id);
    default Page<Song> search(SongSearchRequest request){
        return findAll((Specification<Song>) (root,query,cb) ->{
            List<Predicate> predicates = new ArrayList<>();

            if(request.getName() !=null) {
                predicates.add(cb.equal(root.get("song").get("title"), request.getName()));
                predicates.add(cb.like(root.get("song").get("title"), request.getLikeName()));
            }

            if(request.getGenre() != null) {
                predicates.add(cb.equal(root.get("album").get("genre"), request.getGenre()));
                predicates.add(cb.like(root.get("album").get("genre"), request.getLikeGenre()));
            }
            if(request.getAlbumName() != null) {
                predicates.add(cb.equal(root.get("album").get("title"), request.getAlbumName()));
                predicates.add(cb.like(root.get("album").get("title"), request.getAlbumName()));
            }
            if(request.getTitle() != null) {
                predicates.add(cb.equal(root.get("album").get("artist").get("name"), request.getTitle()));
                predicates.add(cb.like(root.get("album").get("artist").get("name"), request.getTitle()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        },request.pageable());
    }


}
