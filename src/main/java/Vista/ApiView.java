package Vista;

import java.util.List;
import java.util.Map;

public class ApiView {
    public void mostrarAutores(List<String> autores) {
        System.out.println("Autores encontrados:");
        for (String autor : autores) {
            System.out.println(autor);
        }
    }

    public void mostrarTopAutores(List<String> topAutores, Map<String, Integer> conteoArticulos) {
        System.out.println("\nTop 10 Autores con más artículos:");
        for (String autor : topAutores) {
            System.out.println(autor + ": " + conteoArticulos.get(autor));
        }
    }
}
