package Controlador;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


import Modelo.ApiResponse;

public class ApiController {
    private ApiResponse apiResponse;

    public ApiController() {
        this.apiResponse = new ApiResponse();
    }

    public List<ApiResponse.AuthorInfo> obtenerAutores(String query, String apiKey) {
        try {
            return apiResponse.consultarSerpApi(query, apiKey);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            return null; 
        }
    }
}
