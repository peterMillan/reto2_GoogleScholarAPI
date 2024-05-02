package Controlador;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Modelo.ApiResponse;

public class ApiController {
    private ApiResponse apiResponse;

    public ApiController() {
        this.apiResponse = new ApiResponse();
    }

    public List<String> obtenerAutores(String query, String apiKey) {
        try {
            return apiResponse.consultarSerpApi(query, apiKey);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            return null; // Otra acción que desees tomar en caso de error
        }
    }

    public Map<String, Integer> contarArticulosPorAutor(List<String> autores) {
        Map<String, Integer> conteoArticulos = new HashMap<>();

        for (String autor : autores) {
            conteoArticulos.put(autor, conteoArticulos.getOrDefault(autor, 0) + 1);
        }

        return conteoArticulos;
    }

    public List<String> obtenerTopAutores(Map<String, Integer> conteoArticulos) {
        // Ordena los autores por cantidad de artículos en orden descendente
        return conteoArticulos.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
