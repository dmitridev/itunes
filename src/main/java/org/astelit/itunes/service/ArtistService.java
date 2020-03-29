package org.astelit.itunes.service;


import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.artist.ArtistResponse;
import org.astelit.itunes.dto.artist.ArtistSearchRequest;
import org.astelit.itunes.dto.artist.CreateArtistRequest;
import org.astelit.itunes.dto.artist.UpdateArtistRequest;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.repository.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.astelit.itunes.utils.Exceptions.ARTIST_NOT_FOUND;


@RestController
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository repository;

    public ArtistResponse create(CreateArtistRequest request) {
        Artist artist = new Artist();
        artist.setName(request.getName());

        repository.save(artist);
        return new ArtistResponse(artist);
    }

    public ArtistResponse update(UpdateArtistRequest request) {
        Artist artist = repository.findById(request.getId()).orElseThrow(ARTIST_NOT_FOUND);
        artist.setName(request.getName());

        repository.save(artist);
        return new ArtistResponse(artist);
    }

    public ArtistResponse view(Long id) {
        Artist artist = repository.findById(id).orElseThrow(ARTIST_NOT_FOUND);
        return new ArtistResponse(artist);
    }

    public ArtistResponse delete(Long id) {
        Artist artist = repository.findById(id).orElseThrow(ARTIST_NOT_FOUND);
        repository.delete(artist);
        return new ArtistResponse(artist);
    }

    public Page<ArtistResponse> search(ArtistSearchRequest request) {
        return repository.search(request).map(ArtistResponse::new);
    }

    @Transactional
    public List<AlbumResponse> getAlbumsByArtist(Long artistId){
        Artist artist = repository.findById(artistId).orElseThrow(ARTIST_NOT_FOUND);
        return artist.getAlbumsList().stream().map(AlbumResponse::new).collect(Collectors.toList());
    }
}
