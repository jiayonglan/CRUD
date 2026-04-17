package org.ieselcaminas.Plataformas_de_Musica.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "music_streaming")
public class MusicStreaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    private String streamUrl;

    @OneToMany(mappedBy = "streamingService")
    private List<Music> musicList = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getStreamUrl() { return streamUrl; }
    public void setStreamUrl(String streamUrl) { this.streamUrl = streamUrl; }

    public List<Music> getMusicList() { return musicList; }
    public void setMusicList(List<Music> musicList) { this.musicList = musicList; }
}