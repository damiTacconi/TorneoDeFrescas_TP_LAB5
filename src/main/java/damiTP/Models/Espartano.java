package damiTP.Models;

import damiTP.Interfaces.Beber;
import damiTP.Interfaces.Orinar;

public class Espartano extends Humano{
    protected Integer toleranciaExtra;

    public Espartano(String nombre, Integer edad, Integer tE) {
        super(nombre, edad, new OrinarEspartanoImp(tE), new BeberEspartanoImp());
        setToleranciaExtra(tE);
    }
    public Espartano(){super();}
    public Integer getToleranciaExtra() {
        return toleranciaExtra;
    }

    public void setToleranciaExtra(Integer toleranciaExtra) {
        this.toleranciaExtra = toleranciaExtra;
    }
}
