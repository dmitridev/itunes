package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.playlist.PlaylistCreateRequest;
import org.astelit.itunes.dto.playlist.PlaylistResponse;
import org.astelit.itunes.dto.playlist.PlaylistSearchRequest;
import org.astelit.itunes.dto.playlist.PlaylistUpdateRequest;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.User;
import org.astelit.itunes.exception.NotFoundException;
import org.astelit.itunes.repository.PlaylistRepository;
import org.astelit.itunes.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import static org.astelit.itunes.utils.Exceptions.PLAYLIST_NOT_FOUND;
import static org.astelit.itunes.utils.Exceptions.USER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository repository;
    private final UserRepository userRepository;

    public PlaylistResponse create(PlaylistCreateRequest request) {
        User author = userRepository.findById(request.getAuthor()).orElseThrow(USER_NOT_FOUND);

        Playlist playlist = new Playlist();
        playlist.setAuthor(author);
        playlist.setName(request.getName());

        repository.save(playlist);
        return new PlaylistResponse(playlist);
    }

    public PlaylistResponse update(PlaylistUpdateRequest request) {
        Playlist playlist = repository.findById(request.getId()).orElseThrow(PLAYLIST_NOT_FOUND);
        playlist.setName(request.getName());
        repository.save(playlist);
        return new PlaylistResponse(playlist);
    }

    public PlaylistResponse view(long id) {
        Playlist playlist = repository.findById(id).orElseThrow(PLAYLIST_NOT_FOUND);
        return new PlaylistResponse(playlist);
    }

    public Page<PlaylistResponse> search(PlaylistSearchRequest request) {
        return repository.search(request).map(PlaylistResponse::new);
    }
}
