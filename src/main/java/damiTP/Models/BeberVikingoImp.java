package damiTP.Models;

import damiTP.Interfaces.Beber;

import java.util.Random;

public class BeberVikingoImp implements Beber {


    @Override
    public Integer beber() {
        Random r = new Random();

        /* RANDOM MINIMO 1 hASTA 15*/
        return r.nextInt(10 - 1 + 1) + 1; // ( MAX - MIN + 1 ) + 1
    }
}
