package damiTP.Config;

import java.sql.*;

public class ConnectionMySQL {

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWORD = "";
    static private final String DB_URL = "jdbc:mysql://localhost:3306/torneoDeFrescas";
    static private final String OPTIONS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private static final ConnectionMySQL INSTANCE = new ConnectionMySQL();

    public static ConnectionMySQL getInstance(){
        return INSTANCE;
    }

    private ConnectionMySQL(){

            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        try {
            connection = DriverManager.getConnection(DB_URL+OPTIONS,USER, "" );

        } catch (SQLException e) {
            System.out.println("HUBO UN PROBLEMA EN LA BASE DE DATOS: " + e.getMessage());
        }
    }


}
