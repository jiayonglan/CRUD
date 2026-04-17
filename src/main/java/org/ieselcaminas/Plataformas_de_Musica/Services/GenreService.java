package org.ieselcaminas.Plataformas_de_Musica.Services;

import org.ieselcaminas.Plataformas_de_Musica.Entity.Genre;
import org.ieselcaminas.Plataformas_de_Musica.Repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}