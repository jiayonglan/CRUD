package org.ieselcaminas.Plataformas_de_Musica.Services;

import org.ieselcaminas.Plataformas_de_Musica.Entity.Music;
import org.ieselcaminas.Plataformas_de_Musica.Repository.MusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService {

    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> findAll() {
        return musicRepository.findAll();
    }

    public Music save(Music music) {
        return musicRepository.save(music);
    }

    public Optional<Music> findById(Long id) {
        return musicRepository.findById(id);
    }

    public void deleteById(Long id) {
        musicRepository.deleteById(id);
    }
}