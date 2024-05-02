package Vista;

import java.util.List;


import Modelo.ApiResponse;

public class ApiView {
 public void mostrarAutores(List<ApiResponse.AuthorInfo> autoresAnteriores, List<String> autoresActuales) {
        System.out.println("\n-->Autores anteriores:");
        for (ApiResponse.AuthorInfo autor : autoresAnteriores) {
            System.out.println(autor.getName());
        }
        
        System.out.println("\n-->Autores actuales:");
        for (String autor : autoresActuales) {
            System.out.println(autor);
        }
    }

}
