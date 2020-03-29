package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.artist.ArtistResponse;
import org.astelit.itunes.dto.artist.ArtistSearchRequest;
import org.astelit.itunes.dto.artist.CreateArtistRequest;
import org.astelit.itunes.dto.artist.UpdateArtistRequest;
import org.astelit.itunes.service.ArtistService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/artists")
@RequiredArgsConstructor
public class ArtistController {
    public final ArtistService artistService;

    @PostMapping
    public ArtistResponse create(@Valid @RequestBody CreateArtistRequest request) {
        return artistService.create(request);
    }

    @GetMapping("{id}")
    public ArtistResponse read(@PathVariable long id) {
        return artistService.view(id);
    }

    @PatchMapping
    public ArtistResponse update(@Valid @RequestBody UpdateArtistRequest request) {
        return artistService.update(request);
    }

    @DeleteMapping("{id}")
    public ArtistResponse delete(@PathVariable long id) {
        return artistService.delete(id);
    }

    @GetMapping
    public Page<ArtistResponse> search(ArtistSearchRequest request) {
        return artistService.search(request);
    }

    @GetMapping("{id}/albums")
    public List<AlbumResponse> getAllAlbums(@PathVariable("id") Long id){
        return artistService.getAlbumsByArtist(id);
    }
}
