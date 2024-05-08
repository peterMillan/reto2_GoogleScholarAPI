package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Modelo.Author;
import Modelo.AuthorApi;


import java.util.ArrayList;

public class AuthorController {

    public List<Author> obtenerYGuardarAutores(String query, String apiKey) {
        try {
            AuthorApi authorApi = new AuthorApi(); // Crear una instancia de AuthorApi
            List<Author> autores = authorApi.obtenerAutoresAPI(query, apiKey); // Llamar al método desde la instancia creada
            guardarAutoresEnBD(autores);
            return autores;
        } catch (Exception e) {
            // Relanzar la excepción para que el código que llama a este método pueda manejarla
            throw new RuntimeException("Error al obtener o guardar autores", e);
        }
    }

    private void guardarAutoresEnBD(List<Author> autores) {
        List<Author> autoresGuardados = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
            String sql = "INSERT INTO authors (id_author, name, cited_by) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            for (Author autor : autores) {
                if (autorYaRegistradoEnBD(autor.getIdAuthor(), conn)) {
                    System.out.println("El autor " + autor.getName() + " con ID " + autor.getIdAuthor()
                            + " ya estaba registrado en el top.");
                    continue;
                }

                statement.setString(1, autor.getIdAuthor());
                statement.setString(2, autor.getName());
                statement.setInt(3, autor.getCitedBy());
                statement.executeUpdate();
                autoresGuardados.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (autoresGuardados.isEmpty()) {
            System.out.println("No se guardaron autores nuevos.");
        } else {
            System.out.println("Autores guardados correctamente:");
            for (Author autor : autoresGuardados) {
                System.out.println(autor.getName() + " - ID: " + autor.getIdAuthor());
            }
        }
    }

    public List<Author> obtenerAutoresBD() {
        List<Author> autores = new ArrayList<>();
        String sql = "SELECT * FROM authors";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String idAuthor = rs.getString("id_author");
                String name = rs.getString("name");
                int citedBy = rs.getInt("cited_by");
                Author autor = new Author(idAuthor, name, citedBy); // Utiliza el constructor adecuado
                autores.add(autor);
            }
            if (autores.isEmpty()) {
                System.out.println("No hay autores anteriores.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }

    private boolean autorYaRegistradoEnBD(String idAuthor, Connection conn) throws SQLException {
        String sql = "SELECT id_author FROM authors WHERE id_author = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, idAuthor);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
