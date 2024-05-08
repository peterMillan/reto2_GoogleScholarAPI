package Vista;

import java.util.List;
import Modelo.Author;

public class AuthorView {
    public void mostrarAutores(List<Author> autoresAnteriores, List<Author> autoresActuales) {
        if (autoresAnteriores.isEmpty()) {
            System.out.println("No hay autores anteriores.");
        } else {
            System.out.println("\n-->Autores anteriores:");
            for (Author autor : autoresAnteriores) {
                System.out.println(autor.getIdAuthor() + ">>" + autor.getName() + " - Cited by: " + autor.getCitedBy());
            }
        }

        if (autoresActuales != null) {
            if (autoresActuales.isEmpty()) {
                System.out.println("No se encontraron autores actuales.");
            } else {
                System.out.println("\n-->Autores actuales:");
                for (Author autor : autoresActuales) {
                    System.out.println(autor.getIdAuthor() + ">>" + autor.getName() + " - Cited by: " + autor.getCitedBy());
                }
            }
        } else {
            System.out.println("No se pudieron obtener los autores actuales.");
        }
    }
}
