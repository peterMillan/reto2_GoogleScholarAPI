package Controlador;

import java.util.ArrayList;
import java.util.List;
import Modelo.ApiResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApiController {
    // Método para obtener autores y guardarlos en la base de datos
    public List<ApiResponse.AuthorInfo> obtenerAutores(String query, String apiKey) {
        try {
            // Realizar la consulta a la API y obtener los autores
            ApiResponse apiResponse = new ApiResponse();
            List<ApiResponse.AuthorInfo> autores = apiResponse.consultarSerpApi(query, apiKey);

            // Guardar los autores en la base de datos
            guardarAutoresEnBD(autores);

            return autores;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para guardar los autores en la base de datos
    private void guardarAutoresEnBD(List<ApiResponse.AuthorInfo> autores) {
        String url = "jdbc:mysql://localhost:3306/serpapi";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO authors (id_author, name, cited_by) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            for (ApiResponse.AuthorInfo autor : autores) {
                // Verificar si el autor ya está registrado en la base de datos
                if (autorYaRegistradoEnBD(autor.getIdAuthor(), conn)) {
                    System.out.println("El autor " + autor.getName() + "con ID " + autor.getIdAuthor()
                            + " ya estaba registrado en el top.");
                    continue; // Saltar a la siguiente iteración del bucle
                }

                // Si el autor no está registrado, proceder con la inserción
                statement.setString(1, autor.getIdAuthor()); // Insertamos el id del autor
                statement.setString(2, autor.getName());
                statement.setInt(3, autor.getCitedBy());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ApiResponse.AuthorInfo> obtenerAutoresBD() {
        List<ApiResponse.AuthorInfo> autores = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/serpapi";
        String user = "root";
        String password = "root";
        String sql = "SELECT * FROM authors";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String idAuthor = rs.getString("id_author");
                String name = rs.getString("name");
                int citedBy = rs.getInt("cited_by");
                ApiResponse apiResponse = new ApiResponse();
                ApiResponse.AuthorInfo autor = apiResponse.new AuthorInfo(idAuthor, name, citedBy);
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
        // Consulta SQL para verificar si el autor ya está registrado
        String sql = "SELECT id_author FROM authors WHERE id_author = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, idAuthor);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next(); // Devuelve true si se encontró el autor, false si no se encontró
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción SQLException que pueda ocurrir durante la
            // ejecución
            e.printStackTrace();
            throw e; // Re-lanzar la excepción para que el llamador pueda manejarla
        }
    }
}