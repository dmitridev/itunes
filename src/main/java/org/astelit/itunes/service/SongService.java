package org.astelit.itunes.service;


import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.song.CreateSongRequest;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.dto.song.UpdateSongRequest;
import org.astelit.itunes.entity.Song;
import org.astelit.itunes.repository.SongRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.astelit.itunes.utils.Exceptions.SONG_NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class SongService {

    private final SongRepository repository;

    public SongResponse create(CreateSongRequest request){

        Song song = new Song();

        song.setTitle(request.getTitle());
        song.setDuration(request.getDuration());

        repository.save(song);
        return new SongResponse(song);
    }

    public SongResponse update(UpdateSongRequest request){
        Song song = repository.findById(request.getId()).orElseThrow(SONG_NOT_FOUND);

        song.setTitle(request.getTitle());
        song.setDuration(request.getDuration());

        repository.save(song);
        return new SongResponse(song);
    }

    public SongResponse read(long id){
        Song song = repository.findById(id).orElseThrow(SONG_NOT_FOUND);

        return new SongResponse(song);
    }

    public SongResponse delete(long id){
        Song song = repository.findById(id).orElseThrow(SONG_NOT_FOUND);

        repository.delete(song);
        return new SongResponse(song);
    }

    public Page<SongResponse> search(SearchRequest request){
           return repository.findByTitleIsLikeOrderByTitleAsc(request.getQuery(),request.pageable())
                      .map(SongResponse::new);
    }

}
