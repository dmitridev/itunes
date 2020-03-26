package org.astelit.itunes.service;


import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.song.CreateSongRequest;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.dto.song.SongSearchRequest;
import org.astelit.itunes.dto.song.UpdateSongRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.PlaylistSongRelation;
import org.astelit.itunes.entity.Song;
import org.astelit.itunes.repository.AlbumRepository;
import org.astelit.itunes.repository.PlaylistRepository;
import org.astelit.itunes.repository.RelationsRepository;
import org.astelit.itunes.repository.SongRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.astelit.itunes.utils.Exceptions.ALBUM_NOT_FOUND;
import static org.astelit.itunes.utils.Exceptions.SONG_NOT_FOUND;
import static org.astelit.itunes.utils.Exceptions.PLAYLIST_NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class SongService {

    private final SongRepository repository;
    private final AlbumRepository albumRepository;
    private final PlaylistRepository playlistRepository;
    private final RelationsRepository relationsRepository;

    public SongResponse create(CreateSongRequest request) {

        Song song = new Song();

        song.setTitle(request.getTitle());
        song.setDuration(request.getDuration());
        Album album = albumRepository
                .findById(request.getAlbum())
                .orElseThrow(ALBUM_NOT_FOUND);
        song.setAlbum(album);
        repository.save(song);
        return new SongResponse(song);
    }

    public SongResponse update(UpdateSongRequest request) {
        Song song = repository.findById(request.getId()).orElseThrow(SONG_NOT_FOUND);

        song.setTitle(request.getTitle());
        song.setDuration(request.getDuration());

        repository.save(song);
        return new SongResponse(song);
    }

    public SongResponse read(long id) {
        Song song = repository.findById(id).orElseThrow(SONG_NOT_FOUND);

        return new SongResponse(song);
    }

    public SongResponse delete(long id) {
        Song song = repository.findById(id).orElseThrow(SONG_NOT_FOUND);

        repository.delete(song);
        return new SongResponse(song);
    }

    public Page<SongResponse> search(SongSearchRequest request) {
        return repository.search(request).map(SongResponse::new);


    }

    public List<SongResponse> findSongsByAlbum(Long id) {
        return repository.findByAlbum_Id(id)
                .stream()
                .map(SongResponse::new)
                .collect(Collectors.toList());
    }

    public void AddSongToPlaylist(Long id, Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(PLAYLIST_NOT_FOUND);
        Song song = repository.findById(id).orElseThrow(SONG_NOT_FOUND);

        PlaylistSongRelation psr = new PlaylistSongRelation();
        psr.setSongId(song.getId());
        psr.setPlaylistId(playlist.getId());

        playlist.AddToSongs(song);
        song.addToPlaylists(playlist);
        relationsRepository.save(psr);
        repository.save(song);
        playlistRepository.save(playlist);
    }


}
