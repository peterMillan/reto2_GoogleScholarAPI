package Modelo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class AuthorApi {

    public List<Author> obtenerAutoresAPI(String query, String apiKey)
            throws IOException, InterruptedException, URISyntaxException {
        String queryEncoded = URLEncoder.encode(query, "UTF-8");
        String url = "https://serpapi.com/search.json?engine=google_scholar_profiles&mauthors=" + queryEncoded
                + "&api_key=" + apiKey;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return procesarRespuesta(response.body());
    }

    private List<Author> procesarRespuesta(String jsonResponse) {
        List<Author> authors = new ArrayList<>();

        JSONObject json = new JSONObject(jsonResponse);
        JSONArray profiles = json.getJSONArray("profiles");

        for (int i = 0; i < profiles.length(); i++) {
            JSONObject profile = profiles.getJSONObject(i);
            String authorName = profile.getString("name");
            String authorId = profile.getString("author_id");
            int citedBy = 0;

            if (!profile.isNull("cited_by")) {
                citedBy = profile.getInt("cited_by");
            }

            Author author = new Author(authorId, authorName, citedBy);
            authors.add(author);
        }

        return authors;
    }
}
