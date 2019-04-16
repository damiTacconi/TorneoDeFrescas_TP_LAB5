package damiTP.Models;

import damiTP.App;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class BatallaFinal {
    private FinalBoss boss;
    private List<Humano> ganadores;

    public BatallaFinal(FinalBoss boss, List<Humano> ganadores) {
        this.boss = boss;
        this.ganadores = ganadores;
    }

    public Humano comenzar() throws SQLException {
        System.out.println(App.ANSI_BLUE +  "\t\t\t RONDA FINAL !! " + App.ANSI_RESET);
        Humano ganador = null;
        while(ganadores.size() > 0 && !boss.derrotado)
        {
            Humano e = getHumano(ganadores);
            Enfrentamiento en = new Enfrentamiento(boss,e,50);
            en.comenzar();
            ganador = en.getGanador();
            if(ganador instanceof Vikingo || ganador instanceof Espartano) boss.setDerrotado(true);
        }
        return ganador;
    }

    private Humano getHumano(List<Humano> list)
    {
        Random rand = new Random();
        return list.remove(rand.nextInt(list.size()));
    }

}
