import Controlador.ApiController;
import Modelo.ApiResponse;
import Vista.ApiView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApiController apiController = new ApiController();
        ApiView mainView = new ApiView();

        // Aquí proporciona la consulta y la clave de la API
        String query = "Universidad del Norte";
        String apiKey = "4f72ce14f0467b7efe3ec8648c5c854328331bd6af1119a9be68c6b40df39380";

        List<ApiResponse.AuthorInfo> autores = apiController.obtenerAutores(query, apiKey);
        if (autores != null) {
            // Crear una lista de cadenas que contenga el nombre del autor y el número de
            // "cited_by"
            List<String> nombresYCitedBy = new ArrayList<>();
            for (ApiResponse.AuthorInfo autor : autores) {
                String nombreYCitedBy = autor.getName() + " - Cited by: " + autor.getCitedBy();
                nombresYCitedBy.add(nombreYCitedBy);
            }
            mainView.mostrarAutores(nombresYCitedBy);
        } else {
            System.out.println("No se pudieron obtener los autores.");
        }

    }
}
