package damiTP.Models;

import damiTP.Interfaces.Beber;

import java.util.Random;

public class BeberEspartanoImp implements Beber {

    public BeberEspartanoImp(){}

    @Override
    public Integer beber() {
        Random r = new Random();

        /* RANDOM MINIMO 1 HASTA 10*/
        //TODOS LOS QUE BEBAN COMO ESPARTANOS TENDRAN UN RANGO DE HASTA 10 PUNTOS
        return r.nextInt(10 - 1 + 1) + 1; // ( MAX - MIN + 1 ) + 1
    }
}
