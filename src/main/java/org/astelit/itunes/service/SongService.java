package org.astelit.itunes.service;


import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.playlist.PlaylistResponse;
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

import java.util.ArrayList;
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

    public SongResponse AddSongToPlaylist(Long id, Long playlistId) {
        PlaylistSongRelation playlistSongRelation = new PlaylistSongRelation();
        Song song = repository.findById(id).orElseThrow(SONG_NOT_FOUND);
        Playlist p = playlistRepository.findById(playlistId).orElseThrow(PLAYLIST_NOT_FOUND);
        if(song != null && p != null){
            playlistSongRelation.setSongId(id);
            playlistSongRelation.setPlaylistId(playlistId);
            relationsRepository.save(playlistSongRelation);
            return new SongResponse(song);
        }

        return null;

    }

    public List<PlaylistResponse> getPlaylists(Long id){
        List<PlaylistSongRelation> playlists = relationsRepository.findBySongId(id);
        List<Playlist> returnList = new ArrayList<>();
        for(PlaylistSongRelation rel:playlists){
            Playlist p = playlistRepository.findById(rel.getPlaylistId()).orElseThrow(PLAYLIST_NOT_FOUND);
            returnList.add(p);
        }
        return returnList.stream().map(PlaylistResponse::new).collect(Collectors.toList());


    }


}
