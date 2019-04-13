package damiTP.Models;

import damiTP.App;
import damiTP.Database.ResultadoDB;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

public class Enfrentamiento {

    private int max_cervezas;

    private Humano vikingo;
    private int puntosVikingo;
    private Humano espartano;
    private int puntosEspartano;
    private Humano ganador = null;

    public Enfrentamiento() {
    }

    public Enfrentamiento(Vikingo v, Espartano e, int max_c) {
        vikingo = v;
        espartano = e;
        puntosEspartano = 0;
        puntosVikingo = 0;
        max_cervezas = max_c;
    }

    private boolean verificarGanasDeOrinar(Humano h) {
        boolean orinando = false;

        Random r = new Random();
        int g = r.nextInt(5);

        if (g == 3 || g == 1) {
            App.comunicar(App.ANSI_RED + "A " + h.getNombre() + " LE DIERON GANAS DE ORINAR Y ESTA INTENTANDO AGUANTAR !!"
                    + App.ANSI_RESET, App.DEFAULT_DELAY);

            int puntos_orina = h.getOrinar().orinar();
            if (h instanceof Espartano) {
                puntos_orina += ((Espartano) h).getToleranciaExtra();
            }

            if (puntos_orina < 3)
                orinando = true;
            else
                App.comunicar(h.getNombre() + " AH AGUANTADO Y SEGUIRA DISPUESTO A BEBER !!!", App.DEFAULT_DELAY);

        }

        return orinando;
    }

    private void beber(Humano h) {
        int puntos = h.getBeber().beber();

        if (h instanceof Vikingo) {
            puntosVikingo += puntos + ((Vikingo) vikingo).getBebedorProfesional();
        } else if (h instanceof Espartano) {
            puntosEspartano += puntos;
        }
    }

    private void turno(Humano h) {
        if (h instanceof Vikingo) {
            beber(vikingo);
            App.comunicar(vikingo.getNombre() + " CONSIGUIO " + puntosVikingo + " PUNTOS BEBIENDO !!", App.DEFAULT_DELAY);
        } else if (h instanceof Espartano) {
            beber(espartano);
            App.comunicar(espartano.getNombre() + " CONSIGIUIO " + puntosEspartano + " PUNTOS BEBIENDO !!", App.DEFAULT_DELAY);
        }
    }

    private void verificarGanador() throws SQLException {

        if(Objects.isNull(ganador)) {

            if (puntosVikingo >= max_cervezas && puntosVikingo >= puntosEspartano) {
                ganador = vikingo;
                App.comunicar(App.ANSI_GREEN + "EL GANADOR ES EL VIKINGO " + ganador.getNombre() + " !!, LOGRO " + puntosVikingo
                                + " PUNTOS !!" + App.ANSI_RESET,
                        App.DEFAULT_DELAY);
            } else {
                ganador = espartano;
                App.comunicar(App.ANSI_GREEN + "EL GANADOR ES " + ganador.getNombre() + " !!, LOGRO " + puntosEspartano
                                + " PUNTOS !!" + App.ANSI_RESET,
                        App.DEFAULT_DELAY);
            }
        }

        registrarGanador();
    }

    void comenzar() throws SQLException{
        Humano ganador = null;

        App.comunicar("VIKINGO " + vikingo.getNombre() + " VS " + " ESPARTANO " + espartano.getNombre(), App.DEFAULT_DELAY);
        do {

            turno(vikingo);
            boolean orinando = verificarGanasDeOrinar(vikingo);

            if (orinando) {
                App.comunicar(App.ANSI_GREEN + "EL VIKINGO " + vikingo.getNombre() + " SE ORINO !!! EL GANADOR ES EL ESPARTANO "
                        + espartano.getNombre() + " !!!" + App.ANSI_RESET, App.DEFAULT_DELAY);
                ganador = espartano;

            } else {

                turno(espartano);
                orinando = verificarGanasDeOrinar(espartano);

                if (orinando) {
                    App.comunicar(App.ANSI_GREEN + "EL ESPARTANO " + espartano.getNombre() + " SE ORINO !!! EL GANADOR ES EL VIKINGO "
                            + vikingo.getNombre() + " !!!" + App.ANSI_RESET, App.DEFAULT_DELAY);
                    ganador = vikingo;
                }
            }
        } while (Objects.isNull(ganador) && puntosVikingo < max_cervezas && puntosEspartano < max_cervezas) ;

        verificarGanador();
    }



    private void registrarGanador() throws SQLException {
        if (Objects.nonNull(ganador)) {
            if (ganador instanceof Vikingo) {
                Resultado resultado = new Resultado(ganador.getNombre(), puntosVikingo);
                resultado.registrarResultado();
            } else if (ganador instanceof Espartano) {
                Resultado resultado = new Resultado(ganador.getNombre(), puntosEspartano);
                resultado.registrarResultado();
            }
        }
    }

    public Humano getGanador() {
        return ganador;
    }

    public void setGanador(Humano ganador) {
        this.ganador = ganador;
    }

    public int getMax_cervezas() {
        return max_cervezas;
    }

    public void setMax_cervezas(int max_cervezas) {
        this.max_cervezas = max_cervezas;
    }

    public Humano getVikingo() {
        return vikingo;
    }

    public void setVikingo(Humano vikingo) {
        this.vikingo = vikingo;
    }

    public Humano getEspartano() {
        return espartano;
    }

    public void setEspartano(Humano espartano) {
        this.espartano = espartano;
    }
}
