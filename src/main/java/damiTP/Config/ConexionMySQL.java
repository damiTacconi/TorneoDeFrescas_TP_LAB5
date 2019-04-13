package damiTP.Config;

import java.sql.*;

public class ConexionMySQL {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWORD = "";
    static private final String DB_URL = "jdbc:mysql://localhost:3306/torneoDeFrescas";
    static private final String OPTIONS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    private Connection connection;

    private static final ConexionMySQL INSTANCE = new ConexionMySQL();

    public static ConexionMySQL getInstance(){
        return INSTANCE;
    }

    private ConexionMySQL(){

            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

        try {
            connection = DriverManager.getConnection(DB_URL+OPTIONS,USER, "" );

        } catch (SQLException e) {
            System.out.println("HUBO UN PROBLEMA EN LA BASE DE DATOS: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
