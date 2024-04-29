package Modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
    public List<String> consultarSerpApi(String query, String apiKey) throws IOException {
        String url = "https://serpapi.com/search.json?engine=google_scholar&q=" + query + "&api_key=" + apiKey;

        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return procesarRespuesta(response.toString());
        } else {
            throw new IOException("Error al obtener la respuesta de la API. Código de estado: " + responseCode);
        }
    }

    private List<String> procesarRespuesta(String jsonResponse) {
        List<String> authors = new ArrayList<>();

        // Analiza el JSON y extrae los autores según la estructura real de tu JSON de respuesta

        return authors;
    }
}
