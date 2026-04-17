package org.ieselcaminas.Plataformas_de_Musica.Repository;

import org.ieselcaminas.Plataformas_de_Musica.Entity.MusicStreaming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicStreamingRepository extends JpaRepository<MusicStreaming, Long> {
}