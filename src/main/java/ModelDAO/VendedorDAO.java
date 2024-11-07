package ModelDAO;

import Config.DatabaseConnection;
import Model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {

    public static List<Vendedor> Listar() {
        String query = "select Cia_nCodigo , Ven_nCodigo , Ven_cNombre , Ven_cApellidos"
                + " from Ventas.Vendedor "
                + " order by Ven_cApellidos asc , Ven_cNombre asc";
        List<Vendedor> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Vendedor obj = new Vendedor();
                obj.setVenCodigo(resultSet.getInt("Cia_nCodigo"));
                obj.setNombres(resultSet.getString("Ven_cNombre"));
                obj.setApellidos(resultSet.getString("Ven_cApellidos"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
