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
    public List<String> consultarSerpApi(String query, String apiKey)
            throws IOException, InterruptedException, URISyntaxException {
        String queryEncoded = URLEncoder.encode(query, "UTF-8");
        // Construye la URL con los parámetros proporcionados
        String url = "https://serpapi.com/search.json?engine=google_scholar&q=" + queryEncoded + "&api_key=" + apiKey;

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

    private List<String> procesarRespuesta(String jsonResponse) {
        List<String> authors = new ArrayList<>();

        // Analiza el JSON y extrae los autores
        JSONObject json = new JSONObject(jsonResponse);
        JSONArray organicResults = json.getJSONArray("organic_results");

        for (int i = 0; i < organicResults.length(); i++) {
            JSONObject result = organicResults.getJSONObject(i);
            JSONArray authorsArray = result.getJSONObject("publication_info").getJSONArray("authors");

            for (int j = 0; j < authorsArray.length(); j++) {
                String authorName = authorsArray.getJSONObject(j).getString("name");
                authors.add(authorName);
            }
        }

        return authors;
    }
}
