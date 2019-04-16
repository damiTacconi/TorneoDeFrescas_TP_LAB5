package damiTP.Models;

import damiTP.App;
import damiTP.Interfaces.Orinar;

import java.util.Random;

public class OrinarVikingoImp implements Orinar {

    @Override
    public Integer orinar() {
        Random r = new Random();

        // TODOS LOS QUE AGUANTEN COMO VIKINGOS TENDRAN EL SIGUIENTE AGUANTE: RANDOM DEFINIDO EN MAIN + 3 ADICIONALES
        return r.nextInt(App.RANDOM_GANAS_DE_ORINAR + 3);
    }
}
