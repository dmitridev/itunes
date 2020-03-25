package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.CreateAlbumRequest;
import org.astelit.itunes.dto.album.UpdateAlbumRequest;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.repository.AlbumRepository;
import org.astelit.itunes.repository.ArtistRepository;
import org.astelit.itunes.repository.SongRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.astelit.itunes.utils.Exceptions.ALBUM_NOT_FOUND;
import static org.astelit.itunes.utils.Exceptions.ARTIST_NOT_FOUND;


@RestController
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository repository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    public AlbumResponse create(CreateAlbumRequest request){
        Album album = new Album();
        album.setTitle(request.getTitle());
        album.setGenre(request.getGenre());
        album.setReleaseDate(request.getReleaseDate());
        if(request.getArtist() != 0){
            Artist artist = artistRepository.findById(request.getArtist()).orElseThrow(ARTIST_NOT_FOUND);
            album.setArtist(artist);
        }


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



    public List<SongResponse> findSongsByAlbum(Long id){
        return songRepository.findByAlbum_Id(id).stream()
                .map(SongResponse::new)
                .collect(Collectors.toList());
    }
}
