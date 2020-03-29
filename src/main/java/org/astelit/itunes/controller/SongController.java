package org.astelit.itunes.controller;


import lombok.RequiredArgsConstructor;

import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.playlist.PlaylistResponse;
import org.astelit.itunes.dto.song.CreateSongRequest;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.dto.song.SongSearchRequest;
import org.astelit.itunes.dto.song.UpdateSongRequest;
import org.astelit.itunes.service.SongService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/songs")
public class SongController {
    public final SongService songService;

    @PostMapping
    public SongResponse create(@Valid @RequestBody CreateSongRequest request) {
        return songService.create(request);
    }

    @GetMapping("{id}")
    public SongResponse read(@PathVariable long id) {
        return songService.read(id);
    }

    @PatchMapping
    public SongResponse update(@Valid @RequestBody UpdateSongRequest request) {
        return songService.update(request);
    }

    @DeleteMapping("{id}")
    public SongResponse delete(@PathVariable long id) {
        return songService.delete(id);
    }

    @GetMapping
    public Page<SongResponse> search(SongSearchRequest request) {
        return songService.search(request);
    }

    @PostMapping("{id}/playlist/{playlist_id}")
    public void AddToPlaylist(@PathVariable("id") Long id, @PathVariable("playlist_id") Long playlist_id) {
        songService.AddSongToPlaylist(id, playlist_id);
    }

    @GetMapping("{id}/playlists")
    public List<PlaylistResponse> getAllPlaylistsBySong(@PathVariable("id") Long id){
        return songService.getPlaylists(id);
    }


}
