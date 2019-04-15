package damiTP.Models;

import damiTP.Interfaces.Beber;
import damiTP.Interfaces.Orinar;

public class Vikingo extends Humano{
    protected Integer bebedorProfesional;

    public Vikingo(String nombre, Integer edad, Orinar orinar, Beber beber, Integer bebedorProfesional) {
        super(nombre, edad,orinar, beber);
        setBebedorProfesional(bebedorProfesional);
    }

    public Integer getBebedorProfesional() {
        return bebedorProfesional;
    }

    public void setBebedorProfesional(Integer bebedorProfesional) {
        this.bebedorProfesional = bebedorProfesional;
    }



}
