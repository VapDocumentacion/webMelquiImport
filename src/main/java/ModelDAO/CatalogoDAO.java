package ModelDAO;

import Config.DatabaseConnection;
import Model.Catalogo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogoDAO {

    public static List<Catalogo> ListarPorCodigo(String codigo) {
        String sql = "SELECT Cia_nCodigo , Cma_cCodigo , Cma_cDescripcion,Cma_nCodigo "
                + " FROM Logistica.Catalogo "
                + " WHERE Cma_cCodigo LIKE ?";
        List<Catalogo> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + codigo + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Catalogo catalogo = new Catalogo();
                catalogo.setMcanCodigo(resultSet.getInt("Cma_nCodigo"));
                catalogo.setCianCodigo(resultSet.getInt("Cia_nCodigo"));
                catalogo.setCmacCodigo(resultSet.getString("Cma_cCodigo"));
                catalogo.setDescripcion(resultSet.getString("Cma_cDescripcion"));
                lista.add(catalogo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static List<Catalogo> ListarPorDescripcion(String descripcion) {
        String sql = "SELECT Cia_nCodigo , Cma_cCodigo , Cma_cDescripcion,Cma_nCodigo "
                + "  FROM Logistica.Catalogo "
                + " WHERE Cma_cDescripcion LIKE ?";
        List<Catalogo> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + descripcion + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Catalogo catalogo = new Catalogo();
                catalogo.setMcanCodigo(resultSet.getInt("Cma_nCodigo"));
                catalogo.setCianCodigo(resultSet.getInt("Cia_nCodigo"));
                catalogo.setCmacCodigo(resultSet.getString("Cma_cCodigo"));
                catalogo.setDescripcion(resultSet.getString("Cma_cDescripcion"));
                lista.add(catalogo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static Catalogo BuscarPorId(int id) {
        String sql = "SELECT Cma_nCodigo, Cia_nCodigo , Cma_cCodigo , Cma_cDescripcion "
                + "  FROM Logistica.Catalogo "
                + " WHERE Cma_nCodigo = ?";
        Catalogo catalogo = null;

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                catalogo = new Catalogo();
                catalogo.setMcanCodigo(resultSet.getInt("Cma_nCodigo"));
                catalogo.setCianCodigo(resultSet.getInt("Cia_nCodigo"));
                catalogo.setCmacCodigo(resultSet.getString("Cma_cCodigo"));
                catalogo.setDescripcion(resultSet.getString("Cma_cDescripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catalogo;
    }
}
