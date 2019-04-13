package damiTP.Models;

import damiTP.Database.ResultadoDB;

import java.sql.SQLException;

public class Resultado {
    private int id;
    protected String nombre_ganador;
    protected int puntos_cerveza;

    public Resultado(String nombre_ganador, int puntos_cerveza) {
        this.nombre_ganador = nombre_ganador;
        this.puntos_cerveza = puntos_cerveza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_ganador() {
        return nombre_ganador;
    }

    public void setNombre_ganador(String nombre_ganador) {
        this.nombre_ganador = nombre_ganador;
    }

    public int getPuntos_cerveza() {
        return puntos_cerveza;
    }

    public void setPuntos_cerveza(int puntos_cerveza) {
        this.puntos_cerveza = puntos_cerveza;
    }

    public void registrarResultado() throws  SQLException{
        ResultadoDB.getInstance().insertar(this);
    }
}
