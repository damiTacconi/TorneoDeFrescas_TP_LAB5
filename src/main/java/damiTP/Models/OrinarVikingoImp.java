package damiTP.Models;

import damiTP.App;
import damiTP.Interfaces.Orinar;

import java.util.Random;

public class OrinarVikingoImp implements Orinar {

    @Override
    public Integer orinar() {
        System.out.println("VIKINGO AGUANTANDO LAS GANAS DE ORINAR....");
        Random r = new Random();

        return r.nextInt(App.RANDOM_GANAS_DE_ORINAR);
    }
}
