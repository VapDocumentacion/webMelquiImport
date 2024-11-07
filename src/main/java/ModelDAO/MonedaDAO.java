package ModelDAO;

import Config.DatabaseConnection;
import Model.Moneda;
import Model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonedaDAO {

    public static List<Moneda> Listar() {
        String query = "select Mon_nCodigo ,Mon_cDescripcion from Contabilidad.Moneda";
        List<Moneda> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Moneda obj = new Moneda();
                obj.setMonCodigo(resultSet.getInt("Mon_nCodigo"));
                obj.setDescripcion(resultSet.getString("Mon_cDescripcion"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
