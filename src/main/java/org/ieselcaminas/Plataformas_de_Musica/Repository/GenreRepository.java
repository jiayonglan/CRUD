package org.ieselcaminas.Plataformas_de_Musica.Repository;

import org.ieselcaminas.Plataformas_de_Musica.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}