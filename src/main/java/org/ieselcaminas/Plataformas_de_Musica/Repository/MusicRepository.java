package org.ieselcaminas.Plataformas_de_Musica.Repository;

import org.ieselcaminas.Plataformas_de_Musica.Entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {

    List<Music> findByArtist(String artist);

    @Query("SELECT m FROM Music m WHERE m.trackName LIKE %:name% AND m.durationMs > :duration")
    List<Music> findLongSongsByName(@Param("name") String name, @Param("duration") long duration);
}