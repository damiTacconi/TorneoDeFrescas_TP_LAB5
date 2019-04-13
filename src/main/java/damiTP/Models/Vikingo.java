package damiTP.Models;

import damiTP.Interfaces.Beber;
import damiTP.Interfaces.Orinar;

public class Vikingo extends Humano{
    protected Integer bebedorProfesional;

    public Vikingo(){super();}

    public Vikingo(String nombre, Integer edad, Integer bebedorProfesional) {
        super(nombre, edad, new OrinarVikingoImp(), new BeberVikingoImp(bebedorProfesional));
        setBebedorProfesional(bebedorProfesional);
    }

    public Integer getBebedorProfesional() {
        return bebedorProfesional;
    }

    public void setBebedorProfesional(Integer bebedorProfesional) {
        this.bebedorProfesional = bebedorProfesional;
    }



}
