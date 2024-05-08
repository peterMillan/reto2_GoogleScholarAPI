import Controlador.AuthorController;
import Modelo.Author;
import Vista.AuthorView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuthorView view = new AuthorView();
        AuthorController apiController = new AuthorController();

        // Aquí proporciona la consulta y la clave de la API
        String query = "Universidad del Norte";
        String apiKey = "4f72ce14f0467b7efe3ec8648c5c854328331bd6af1119a9be68c6b40df39380";

        // Obtener autores anteriores de la base de datos
        List<Author> autoresAnteriores = apiController.obtenerAutoresBD();

        // Obtener autores actuales de la API
        List<Author> autoresActuales = apiController.obtenerYGuardarAutores(query, apiKey);

        if (autoresActuales != null) {
            // Mostrar autores anteriores y actuales en la vista
            view.mostrarAutores(autoresAnteriores, autoresActuales);
        } else {
            System.out.println("No se pudieron obtener los autores actuales.");
        }
    }
}
