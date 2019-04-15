package damiTP.Models;

import damiTP.Interfaces.Beber;
import damiTP.Interfaces.Orinar;

public class Espartano extends Humano{
    protected Integer toleranciaExtra;

    public Espartano(String nombre, Integer edad, Orinar orinar, Beber beber, Integer tE) {
        super(nombre, edad, orinar,beber);
        setToleranciaExtra(tE);
    }

    public Integer getToleranciaExtra() {
        return toleranciaExtra;
    }

    public void setToleranciaExtra(Integer toleranciaExtra) {
        this.toleranciaExtra = toleranciaExtra;
    }
}
