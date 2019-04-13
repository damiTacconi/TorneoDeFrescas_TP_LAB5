package damiTP.Models;

import damiTP.App;
import damiTP.Interfaces.Orinar;

import java.util.Random;

public class OrinarEspartanoImp extends Espartano implements Orinar {

    public OrinarEspartanoImp(int tE){super();toleranciaExtra=tE; }

    @Override
    public Integer orinar() {
        System.out.println("ESPARTANO AGUANTANDO LAS GANAS DE ORINAR....");
        Random r = new Random();

        return r.nextInt(App.RANDOM_GANAS_DE_ORINAR + toleranciaExtra + 2);
    }
}
