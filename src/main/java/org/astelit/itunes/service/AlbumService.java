package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.CreateAlbumRequest;
import org.astelit.itunes.dto.album.UpdateAlbumRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.repository.AlbumRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import static org.astelit.itunes.utils.Exceptions.ALBUM_NOT_FOUND;


@RestController
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository repository;

    public AlbumResponse create(CreateAlbumRequest request){
        Album album = new Album();
        album.setTitle(request.getTitle());
        album.setGenre(request.getGenre());
        album.setReleaseDate(request.getReleaseDate());

        repository.save(album);
        return new AlbumResponse(album);
    }

    public AlbumResponse update(UpdateAlbumRequest request){
        Album album = repository.findById(request.getId()).orElseThrow(ALBUM_NOT_FOUND);

        album.setTitle(request.getTitle());
        album.setReleaseDate(request.getReleaseDate());
        album.setGenre(request.getGenre());
        repository.save(album);

        return new AlbumResponse(album);
    }

    public AlbumResponse read(long id){
        Album album = repository.findById(id).orElseThrow(ALBUM_NOT_FOUND);
        return new AlbumResponse(album);
    }

    public AlbumResponse delete(long id){
        Album album = repository.findById(id).orElseThrow(ALBUM_NOT_FOUND);

        repository.delete(album);
        return new AlbumResponse(album);
    }

    public Page<AlbumResponse> search(SearchRequest request){
        return repository.findByTitleIsLikeOrderByTitleAsc(request.getQuery(),request.pageable())
                .map(AlbumResponse::new);
    }




}
