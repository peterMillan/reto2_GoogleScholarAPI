package Modelo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiResponse {
    public List<AuthorInfo> consultarSerpApi(String query, String apiKey)
            throws IOException, InterruptedException, URISyntaxException {
        String queryEncoded = URLEncoder.encode(query, "UTF-8");
        // Construye la URL con los parámetros proporcionados
        String url = "https://serpapi.com/search.json?engine=google_scholar_profiles&mauthors=" + queryEncoded + "&api_key=" + apiKey;

        // Crea una instancia de HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Crea una solicitud GET con la URL
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();

        // Envía la solicitud y obtiene la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Procesa la respuesta JSON y extrae los autores
        return procesarRespuesta(response.body());
    }

    private List<AuthorInfo> procesarRespuesta(String jsonResponse) {
        List<AuthorInfo> authorInfos = new ArrayList<>();
    
        // Analiza el JSON y extrae los autores con sus cited_by
        JSONObject json = new JSONObject(jsonResponse);
        JSONArray profiles = json.getJSONArray("profiles");
    
        for (int i = 0; i < profiles.length(); i++) {
            JSONObject profile = profiles.getJSONObject(i);
            String authorName = profile.getString("name");
            int citedBy = 0; // Valor predeterminado si "cited_by" es nulo
    
            if (!profile.isNull("cited_by")) {
                citedBy = profile.getInt("cited_by");
            }
    
            AuthorInfo authorInfo = new AuthorInfo(authorName, citedBy);
            authorInfos.add(authorInfo);
        }
    
        return authorInfos;
    }
    

    // Clase para representar la información de un autor
    public class AuthorInfo {
        private String name;
        private int citedBy;

        public AuthorInfo(String name, int citedBy) {
            this.name = name;
            this.citedBy = citedBy;
        }

        public String getName() {
            return name;
        }

        public int getCitedBy() {
            return citedBy;
        }
    }
}
