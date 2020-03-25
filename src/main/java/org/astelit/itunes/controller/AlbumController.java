package org.astelit.itunes.controller;


import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.dto.album.CreateAlbumRequest;
import org.astelit.itunes.dto.album.UpdateAlbumRequest;
import org.astelit.itunes.dto.artist.ArtistResponse;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public AlbumResponse create(@Valid @RequestBody CreateAlbumRequest request){
        return albumService.create(request);
    }

    @PatchMapping
    public AlbumResponse update(@Valid @RequestBody UpdateAlbumRequest request){
        return albumService.update(request);
    }

    @GetMapping("{id}")
    public AlbumResponse read(@PathVariable long id){
        return albumService.read(id);
    }

    @DeleteMapping("{id}")
    public AlbumResponse delete(@PathVariable long id){
        return albumService.delete(id);
    }

    @GetMapping
    public Page<AlbumResponse> search(AlbumSearchRequest request){
        return albumService.search(request);
    }

    @GetMapping("{id}/songs")
    public List<SongResponse> findSongsById(@PathVariable("id") long id){
        return albumService.findSongsByAlbum(id);
    }

}
