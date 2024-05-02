import Controlador.ApiController;
import Vista.ApiView;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApiController apiController = new ApiController();
        ApiView mainView = new ApiView();

        // Aquí proporciona la consulta y la clave de la API
        String query = "Universidad del Norte de México";
        String apiKey = "4f72ce14f0467b7efe3ec8648c5c854328331bd6af1119a9be68c6b40df39380";

        List<String> autores = apiController.obtenerAutores(query, apiKey);
        if (autores != null) {
            mainView.mostrarAutores(autores);

            // Contar la cantidad de artículos por autor
            Map<String, Integer> conteoArticulos = apiController.contarArticulosPorAutor(autores);

            // Obtener los 10 autores con más artículos
            List<String> topAutores = apiController.obtenerTopAutores(conteoArticulos);
            mainView.mostrarTopAutores(topAutores, conteoArticulos);
        } else {
            System.out.println("No se pudieron obtener los autores.");
        }
    }
}
