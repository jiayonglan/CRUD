package org.ieselcaminas.Plataformas_de_Musica.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class MusicApiService {

    public String getTrackInfo(String artistName, String trackName) throws Exception {
        // Combinamos artista y canción para una búsqueda precisa
        // Ejemplo: "Queen Bohemian Rhapsody" -> "Queen+Bohemian+Rhapsody"
        String query = artistName + " " + trackName;
        String encodedQuery = query.replace(" ", "+");

        // Cambiamos el entity a "musicTrack" para buscar canciones específicamente
        String url = "https://itunes.apple.com/search?term=" + encodedQuery + "&entity=musicTrack&limit=1";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            JsonNode results = root.path("results");

            if (results.isArray() && results.size() > 0) {
                // Ahora podemos sacar más info: nombre del álbum y género
                String album = results.get(0).path("collectionName").asText();
                String genre = results.get(0).path("primaryGenreName").asText();
                return "Álbum: " + album + " | Género: " + genre;
            }
            return "Información no encontrada en iTunes";
        } else {
            throw new RuntimeException("Error de conexión con la API");
        }
    }
}