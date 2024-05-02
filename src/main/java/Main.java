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

        // Obtener autores anteriores de la base de datos
        List<ApiResponse.AuthorInfo> autoresAnteriores = apiController.obtenerAutoresBD();

        // Obtener autores actuales de la API
        List<ApiResponse.AuthorInfo> autoresActuales = apiController.obtenerAutores(query, apiKey);

        if (autoresActuales != null) {
            // Crear una lista de cadenas que contenga el nombre del autor y el número de
            // "cited_by" para los autores actuales
            List<String> nombresYCitedBy = new ArrayList<>();
            for (ApiResponse.AuthorInfo autor : autoresActuales) {
                String nombreYCitedBy = autor.getIdAuthor()+">>"+autor.getName() + " - Cited by: " + autor.getCitedBy();
                nombresYCitedBy.add(nombreYCitedBy);
            }
            // Mostrar autores anteriores y actuales en la vista
            mainView.mostrarAutores(autoresAnteriores, nombresYCitedBy);
        } else {
            System.out.println("No se pudieron obtener los autores actuales.");
        }

    }
}
