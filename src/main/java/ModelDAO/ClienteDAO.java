package ModelDAO;

import Config.DatabaseConnection;
import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static List<Cliente> BuscarPorRuc(String ruc) {
        String query = "SELECT Cli_nCodigo, Cli_cRuc, Cli_cRazonSocial, Cli_cDireccion"
                + "  FROM Ventas.Cliente "
                + " WHERE Cli_cRuc like ?";
        List<Cliente> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + ruc + "%");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setClinCodigo(resultSet.getInt("Cli_nCodigo"));
                cliente.setRuc(resultSet.getString("Cli_cRuc"));
                cliente.setRazonSocial(resultSet.getString("Cli_cRazonSocial"));
                cliente.setDireccion(resultSet.getString("Cli_cDireccion"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static List<Cliente> BuscarPorRazonSocial(String razonSocial) {
        String query = "SELECT Cli_nCodigo, Cli_cRuc, Cli_cRazonSocial, Cli_cDireccion "
                + " FROM Ventas.Cliente "
                + " WHERE Cli_cRazonSocial LIKE ?";
        List<Cliente> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + razonSocial + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cliente obj = new Cliente();
                obj.setClinCodigo(resultSet.getInt("Cli_nCodigo"));
                obj.setRuc(resultSet.getString("Cli_cRuc"));
                obj.setRazonSocial(resultSet.getString("Cli_cRazonSocial"));
                obj.setDireccion(resultSet.getString("Cli_cDireccion"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
