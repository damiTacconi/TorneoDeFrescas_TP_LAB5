package damiTP.Models;

import damiTP.App;
import damiTP.Interfaces.Orinar;

import java.util.Random;

public class OrinarEspartanoImp  implements Orinar {


    @Override
    public Integer orinar() {
        Random r = new Random();

        //LOS QUE AGUANTEN COMO ESPARTANO TENDRAN EL SIGUIENTE AGUANTE:
        // RANGOM DEFINIDO EN MAIN + 4 ADICIONALES + TOLERANCIA-EXTRA
        return r.nextInt(App.RANDOM_GANAS_DE_ORINAR + 4);
    }
}
