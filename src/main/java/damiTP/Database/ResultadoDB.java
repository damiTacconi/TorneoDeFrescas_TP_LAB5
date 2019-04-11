package damiTP.Database;

import damiTP.Config.ConexionMySQL;
import damiTP.Models.Resultado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultadoDB {

    private final String TABLE_NAME = "resultados";
    private static final ResultadoDB INSTANCE = new ResultadoDB();

    private ResultadoDB(){
        ConexionMySQL c = ConexionMySQL.getInstance();

        try {
            Statement statement = c.getConnection().createStatement();
            statement.executeUpdate("TRUNCATE TABLE " + TABLE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultadoDB getInstance(){
        return INSTANCE;
    }


    public void insertar(Resultado resultado) throws SQLException{
        ConexionMySQL c = ConexionMySQL.getInstance();
        try {

            PreparedStatement preparedStatement = c.getConnection().prepareStatement(
                    "INSERT INTO " + TABLE_NAME + " (nombre_ganador, puntos_cerveza) VALUES (?,?)");
            preparedStatement.setString(1, resultado.getNombre_ganador());
            preparedStatement.setInt(2,resultado.getPuntos_cerveza());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Resultado> traerTodo(){
        List<Resultado> resultados = new ArrayList<>();
        ConexionMySQL c = ConexionMySQL.getInstance();

        try {
            Statement statement = c.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);

            while(resultSet.next()){
                resultados.add(
                        new Resultado(resultSet.getString("nombre_ganador"),
                                resultSet.getInt("puntos_cerveza") )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultados;
    }
}
