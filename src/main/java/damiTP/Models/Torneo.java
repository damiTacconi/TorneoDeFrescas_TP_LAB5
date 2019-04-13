package damiTP.Models;

import damiTP.App;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Torneo {

    private List<Enfrentamiento> enfrentamientos;
    private List<Humano> vikingos;
    private List<Humano> espartanos;
    private String nombre_equipo_ganador;

    public Torneo(List<Humano> vikingos, List<Humano> espartanos) {
        this.vikingos = vikingos;
        this.espartanos = espartanos;
        this.enfrentamientos = new ArrayList<>();
        nombre_equipo_ganador = "";
    }

    public void comenzar() throws SQLException {
        int ronda = 1;
        while(vikingos.size() > 0 && espartanos.size() > 0)
        {
            System.out.println(App.ANSI_BLUE +  "\t\t\t RONDA N° " + ronda++ + App.ANSI_RESET);
            Humano e = getHumano(espartanos);
            Humano v = getHumano(vikingos);

            Enfrentamiento en = new Enfrentamiento((Vikingo) v,(Espartano) e,50);
            en.comenzar();
            enfrentamientos.add(en);

        }

        long victorias_vikingos = enfrentamientos.stream()
                .filter(en -> en.getGanador() instanceof Vikingo)
                .count();
        long victorias_espartanos = enfrentamientos.stream()
                .filter(en -> en.getGanador() instanceof Espartano)
                .count();

        App.comunicar("LOS VIKiNGOS GANARON " + victorias_vikingos + " VECES ! " +
                "\n LOS ESPARTANOS GANARON " + victorias_espartanos + " VECES ! " , App.DEFAULT_DELAY);

        nombre_equipo_ganador = victorias_espartanos >= victorias_vikingos ? "ESPARTANOS" : "VIKINGOS";
    }

    private Humano getHumano(List<Humano> list)
    {
        Random rand = new Random();
        return list.remove(rand.nextInt(list.size()));
    }

    public String getNombre_equipo_ganador() {
        return nombre_equipo_ganador;
    }
}
