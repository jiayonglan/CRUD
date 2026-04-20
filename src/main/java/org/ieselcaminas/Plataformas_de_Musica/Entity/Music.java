package org.ieselcaminas.Plataformas_de_Musica.Entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "music")
public class Music implements Comparable<Music> { // Implementa Comparable para cumplir requisito 2.3

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String trackName;

    @Column(nullable = false)
    private String artist;

    private long durationMs;

    // Relación ManyToOne con MusicStreaming
    @ManyToOne
    @JoinColumn(name = "service_id")
    private MusicStreaming streamingService;

    // Relación ManyToOne con Genre
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    // Constructor vacío
    public Music() {
    }

    // Constructor de conveniencia
    public Music(String trackName, String artist, long durationMs) {
        this.trackName = trackName;
        this.artist = artist;
        this.durationMs = durationMs;
    }

    // --- Getters y Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTrackName() { return trackName; }
    public void setTrackName(String trackName) { this.trackName = trackName; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public long getDurationMs() { return durationMs; }
    public void setDurationMs(long durationMs) { this.durationMs = durationMs; }

    public MusicStreaming getStreamingService() { return streamingService; }
    public void setStreamingService(MusicStreaming streamingService) { this.streamingService = streamingService; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    // --- Implementación de Comparable ---
    @Override
    public int compareTo(Music other) {
        // Ordenación natural por nombre de canción
        if (this.trackName == null || other.getTrackName() == null) {
            return 0;
        }
        return this.trackName.compareToIgnoreCase(other.getTrackName());
    }

    // --- Equals y HashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music music = (Music) o;
        return Objects.equals(id, music.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // --- ToString para depuración ---
    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", trackName='" + trackName + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}