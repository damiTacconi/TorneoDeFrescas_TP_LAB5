package damiTP.Models;

import damiTP.Interfaces.Beber;
import damiTP.Interfaces.Orinar;

public class Humano {
    protected String nombre;
    protected Integer edad;
    protected Orinar orinar;
    protected Beber beber;

    public Humano(){}
    public Humano(String nombre, Integer edad, Orinar orinar, Beber beber) {
        this.nombre = nombre;
        this.edad = edad;
        this.orinar = orinar;
        this.beber = beber;
    }

    @Override
    public String toString() {
        return "Humano{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", orinar=" + orinar +
                ", beber=" + beber +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Orinar getOrinar() {
        return orinar;
    }

    public void setOrinar(Orinar orinar) {
        this.orinar = orinar;
    }

    public Beber getBeber() {
        return beber;
    }

    public void setBeber(Beber beber) {
        this.beber = beber;
    }
}
