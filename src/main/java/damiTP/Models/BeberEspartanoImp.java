package damiTP.Models;

import damiTP.Interfaces.Beber;

import java.util.Random;

public class BeberEspartanoImp implements Beber {

    public BeberEspartanoImp(){}

    @Override
    public Integer beber() {
        Random r = new Random();

        /* RANDOM MINIMO 1 HASTA 10*/
        return r.nextInt(15 - 1 + 1) + 1; // ( MAX - MIN + 1 ) + 1
    }
}
