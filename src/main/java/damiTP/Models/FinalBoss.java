package damiTP.Models;

import damiTP.Interfaces.Beber;
import damiTP.Interfaces.Orinar;

public class FinalBoss extends Humano {
    protected int bebedorProfesional;
    protected int toleranciaExtra;
    protected boolean derrotado;

    public FinalBoss(String nombre, Integer edad, Orinar orinar, Beber beber, int bebedorProfesional, int toleranciaExtra) {
        super(nombre, edad, orinar, beber);
        this.bebedorProfesional = bebedorProfesional;
        this.toleranciaExtra = toleranciaExtra;
        derrotado = false;
    }

    public void setDerrotado(boolean derrotado) {
        this.derrotado = derrotado;
    }

    public int getBebedorProfesional() {
        return bebedorProfesional;
    }

    public int getToleranciaExtra() {
        return toleranciaExtra;
    }
}
