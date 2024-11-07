package Config;

import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() {
        String url2 ="jdbc:sqlserver://DESKTOP-L6OMQMR\\DESA_TIP:1433;databaseName=MEQUI_IMPORT;encrypt=true;trustServerCertificate=true;";
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://DESKTOP-L6OMQMR\\DESA_TIP:1433;databaseName=mequi_import";
            String url = url2;
            String usr = "admmeq";
            String psw = "Sistemas2024$$";
            con = DriverManager.getConnection(url, usr, psw);
            System.out.println("Conexion establecida");
        } catch (Exception ex) {
            System.out.println("Error en la conexión: " + ex.getMessage());
            ex.printStackTrace();
        }
        return con;
    }
}