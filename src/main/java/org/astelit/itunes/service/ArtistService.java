package org.astelit.itunes.service;



import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.artist.ArtistResponse;
import org.astelit.itunes.dto.artist.CreateArtistRequest;
import org.astelit.itunes.dto.artist.UpdateArtistRequest;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.repository.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static org.astelit.itunes.utils.Exceptions.ARTIST_NOT_FOUND;


@RestController
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;

    public ArtistResponse create(CreateArtistRequest request){
        Artist artist = new Artist();
        artist.setName(request.getName());
        //artist.setAlbumsList(request.getAlbumsList());

        repository.save(artist);

        return new ArtistResponse(artist);
    }

    public ArtistResponse update(UpdateArtistRequest request){
       Artist artist = repository.findById(request.getId()).orElseThrow(ARTIST_NOT_FOUND);
       artist.setName(request.getName());
       //artist.setAlbumsList(request.getAlbumsList());

       repository.save(artist);
       return new ArtistResponse(artist);
    }

    public ArtistResponse view(Long id){
        Artist artist = repository.findById(id).orElseThrow(ARTIST_NOT_FOUND);
        return new ArtistResponse(artist);
    }

    public ArtistResponse delete(Long id){
        Artist artist = repository.findById(id).orElseThrow(ARTIST_NOT_FOUND);
        repository.delete(artist);
        return new ArtistResponse(artist);
    }

    public Page<ArtistResponse> search(SearchRequest request){
        return repository.findByNameIsLikeOrderByNameAsc(request.getQuery(),request.pageable()).map(ArtistResponse::new);
    }


}