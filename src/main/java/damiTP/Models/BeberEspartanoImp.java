package damiTP.Models;

import damiTP.Interfaces.Beber;

import java.util.Random;

public class BeberEspartanoImp implements Beber {

    public BeberEspartanoImp(){}

    @Override
    public Integer beber() {
        System.out.println("ESPARTANO BEBIENDO....");
        Random r = new Random();

        /* RANDOM MINIMO 1 HASTA 10*/
        return r.nextInt(10 - 1 + 1) + 1; // ( MAX - MIN + 1 ) + 1
    }
}
