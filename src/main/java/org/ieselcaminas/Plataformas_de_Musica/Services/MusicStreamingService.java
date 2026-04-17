package org.ieselcaminas.Plataformas_de_Musica.Services;

import org.ieselcaminas.Plataformas_de_Musica.Entity.MusicStreaming;
import org.ieselcaminas.Plataformas_de_Musica.Repository.MusicStreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicStreamingService {

    private final MusicStreamingRepository musicStreamingRepository;

    public MusicStreamingService(MusicStreamingRepository musicStreamingRepository) {
        this.musicStreamingRepository = musicStreamingRepository;
    }

    public List<MusicStreaming> findAll() {
        return musicStreamingRepository.findAll();
    }

    public MusicStreaming save(MusicStreaming streaming) {
        return musicStreamingRepository.save(streaming);
    }

    public Optional<MusicStreaming> findById(Long id) {
        return musicStreamingRepository.findById(id);
    }

    public void deleteById(Long id) {
        musicStreamingRepository.deleteById(id);
    }
}