package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.playlist.PlaylistCreateRequest;
import org.astelit.itunes.dto.playlist.PlaylistResponse;
import org.astelit.itunes.dto.playlist.PlaylistSearchRequest;
import org.astelit.itunes.dto.playlist.PlaylistUpdateRequest;
import org.astelit.itunes.service.PlaylistService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    public PlaylistResponse create(PlaylistCreateRequest request) {
        return playlistService.create(request);
    }

    @PatchMapping
    public PlaylistResponse update(PlaylistUpdateRequest request) {
        return playlistService.update(request);
    }

    @GetMapping("{id}/playlists")
    public PlaylistResponse read(@PathVariable("id") long id) {
        return playlistService.view(id);
    }

    @GetMapping
    public Page<PlaylistResponse> search(PlaylistSearchRequest request) {
        return playlistService.search(request);
    }

}
