package ModelDAO;

import Config.DatabaseConnection;
import Model.Serie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerieDAO {

    public static List<Serie> Listar() {
        String query = "SELECT Cia_nCodigo, Ser_nCodigo, Ser_cCodigo, Ser_nCorrelativo FROM Ventas.Serie";
        List<Serie> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Serie serie = new Serie();
                serie.setCiaNCodigo(resultSet.getInt("Cia_nCodigo"));
                serie.setSerNCodigo(resultSet.getInt("Ser_nCodigo"));
                serie.setSerCCodigo(resultSet.getString("Ser_cCodigo"));
                serie.setSerNCorrelativo(resultSet.getInt("Ser_nCorrelativo"));
                lista.add(serie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
