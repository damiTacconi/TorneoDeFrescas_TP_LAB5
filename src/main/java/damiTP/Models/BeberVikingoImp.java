package damiTP.Models;

import damiTP.Interfaces.Beber;

import java.util.Random;

public class BeberVikingoImp implements Beber {


    @Override
    public Integer beber() {
        Random r = new Random();

        /* RANDOM MINIMO 1 hASTA 8*/
        // LOS QUE BEBAN COMO VIKINGOS TENDRAN UN RANDOM DE HASTA 8 PUNTOS + EL ATRIBUTO EXTRA
        return r.nextInt(8 - 1 + 1) + 1; // ( MAX - MIN + 1 ) + 1
    }
}
