package damiTP.Models;

import damiTP.Interfaces.Beber;

import java.util.Random;

public class BeberVikingoImp extends Vikingo implements Beber {

    public BeberVikingoImp(int bebedor){super(); bebedorProfesional = bebedor;}

    @Override
    public Integer beber() {
        System.out.println("VIKINGO BEBIENDO....");
        Random r = new Random();

        /* RANDOM MINIMO 1 hASTA 15*/
        return r.nextInt(10 - 1 + 1) + 1 + bebedorProfesional; // ( MAX - MIN + 1 ) + 1
    }
}
