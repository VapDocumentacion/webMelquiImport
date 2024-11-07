package ModelDAO;

import Config.DatabaseConnection;
import Model.DocumentoCab;
import Model.DocumentoDet;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DocumentoFactDAO {

    public static String insertarDocumentoCab(DocumentoCab documento, List<DocumentoDet> documentoDetList) {
        String docNumero = null;
        int docnNumero= 0;
      
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            String sql = "{CALL sp_InsertarDocumentoCab(?, ?, ?, ?, ?, ?, ?, ?)}";
            try (CallableStatement statement = connection.prepareCall(sql)) {
                statement.setInt(1, documento.getSerNCodigo());
                statement.setString(2, documento.getDocFFecDoc());
                statement.setString(3, documento.getDocFFecVencimiento());
                statement.setDouble(4, documento.getTcaNValorVenta());
                statement.setInt(5, documento.getMonNCodigo());
                statement.setInt(6, documento.getVenNCodigo());
                statement.setString(7, documento.getDocCGlosa());
                statement.setInt(8, documento.getCliNCodigo());

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    docNumero = resultSet.getString("Doc_cNumero");
                    docnNumero = resultSet.getInt("Doc_nNumero");
                }

                if (docNumero != null) {
                    for (int i = 0; i < documentoDetList.size(); i++) {
                        DocumentoDet item = documentoDetList.get(i);
                        String sqlDetalle = "{CALL sp_InsertarDocumentoDet(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                        try (CallableStatement statementDetalle = connection.prepareCall(sqlDetalle)) {
                            statementDetalle.setInt(1, docnNumero);
                            statementDetalle.setInt(2, (i + 1)); 
                            statementDetalle.setInt(3, item.getMcanCodigo());
                            statementDetalle.setString(4, item.getCmacCodigo());
                            statementDetalle.setString(5, item.getDescripcion());
                            statementDetalle.setInt(6, item.getCantidad()); 
                            statementDetalle.setDouble(7, item.getValorUnitario());
                            statementDetalle.setDouble(8, item.getDescuento());
                            statementDetalle.setDouble(9, item.getSubTotal());
                            statementDetalle.setDouble(10, item.getIgvValor());
                            statementDetalle.setDouble(11, item.getIgvPorcentaje());

                            statementDetalle.executeUpdate();
                        }
                    }
                    connection.commit();
                }

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docNumero;
    }

}
