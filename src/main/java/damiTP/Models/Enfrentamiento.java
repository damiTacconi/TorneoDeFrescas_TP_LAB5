package damiTP.Models;

import damiTP.App;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

public class Enfrentamiento {

    private int max_cervezas;

    private Humano humano1;
    private int puntosHumano1;
    private Humano humano2;
    private int puntosHumano2;
    private Humano ganador = null;
    private boolean turno = true;
    public Enfrentamiento() {
    }

    public Enfrentamiento(Humano h1, Humano h2, int max_c) {
        humano1 = h1;
        humano2 = h2;
        puntosHumano2 = 0;
        puntosHumano1 = 0;
        max_cervezas = max_c;
    }

    private boolean verificarGanasDeOrinar(Humano h) {
        boolean orinando = false;

        /*
        * RANDOM PARA SABER SI AL HUMANO LE DAN GANAS DE IR AL BAÑO
         */
        Random r = new Random();
        int g = r.nextInt(5);

        if (g == 3) {

            // AL HUMANO LE DIERON GANAS DE ORINAR, AHORA SE DEBE EVALUAR SI SE ORINA O SI LOGRA AGUANTAR

            App.comunicar(App.ANSI_RED + "AL "+ getNombreRaza(h)
                    + " " + h.getNombre()
                    + " LE DIERON GANAS DE ORINAR Y ESTA INTENTANDO AGUANTAR !!"
                    + App.ANSI_RESET, App.DEFAULT_DELAY);

            int puntos_orina = h.getOrinar().orinar();

            if(h instanceof Espartano)
                puntos_orina += ((Espartano) h).getToleranciaExtra();
            else if( h instanceof FinalBoss)
                puntos_orina += ((FinalBoss) h).getToleranciaExtra();



            App.comunicar("!!PUNTOS ORINA : " +  puntos_orina , App.DEFAULT_DELAY);
            if (puntos_orina < (App.RANDOM_GANAS_DE_ORINAR/2))
                orinando = true;
            else
                App.comunicar(App.ANSI_PURPLE +
                        h.getNombre() + " AH AGUANTADO Y SEGUIRA DISPUESTO A BEBER !!!" + App.ANSI_RESET, App.DEFAULT_DELAY);

        }

        return orinando;
    }

    private int beber(Humano h) {
        int puntos = h.getBeber().beber();

        if (h instanceof Vikingo) {
            puntos +=  ((Vikingo) h).getBebedorProfesional();
        } else if (h instanceof  FinalBoss) {
            puntos += ((FinalBoss) h).getBebedorProfesional();
        }

        return puntos;
    }

    private void turno(Humano h) {
            int total;
            if(turno) total = puntosHumano1 += beber(h);
            else total = puntosHumano2 += beber(h);

            App.comunicar(h.getNombre() + " CONSIGUIO " + total + " PUNTOS BEBIENDO !!",
                    App.DEFAULT_DELAY);
            turno = !turno;
    }

    private void verificarGanador() throws SQLException {

        if(Objects.isNull(ganador)) {

            if (puntosHumano1 >= max_cervezas && puntosHumano1 >= puntosHumano2) {
                ganador = humano1;
                App.comunicar(App.ANSI_GREEN + "EL GANADOR ES EL "+ getNombreRaza(humano1) + " " +ganador.getNombre() + " !!, LOGRO " + puntosHumano1
                                + " PUNTOS !!" + App.ANSI_RESET,
                        App.DEFAULT_DELAY);
            } else {
                ganador = humano2;
                App.comunicar(App.ANSI_GREEN + "EL GANADOR ES EL " + getNombreRaza(humano2) + " " +ganador.getNombre() + " !!, LOGRO " + puntosHumano2
                                + " PUNTOS !!" + App.ANSI_RESET,
                        App.DEFAULT_DELAY);
            }
        }

        if(!(ganador instanceof FinalBoss))
            registrarGanador();
    }

    void comenzar() throws SQLException{
        App.comunicar(getNombreRaza(humano1)+ " " + humano1.getNombre() + " VS " + getNombreRaza(humano2) + " " + humano2.getNombre(), App.DEFAULT_DELAY);
        do {

            turno(humano1);
            boolean orinando = verificarGanasDeOrinar(humano1);

            if (orinando) {
                App.comunicar(App.ANSI_GREEN + "EL "+ getNombreRaza(humano1) + " " + humano1.getNombre() +
                        " SE ORINO !!! EL GANADOR ES EL " + getNombreRaza(humano2) + " "
                        + humano2.getNombre() + " !!!" + App.ANSI_RESET, App.DEFAULT_DELAY);
                ganador = humano2;

            } else {

                turno(humano2);
                orinando = verificarGanasDeOrinar(humano2);

                if (orinando) {
                    App.comunicar(App.ANSI_GREEN + "EL " + getNombreRaza(humano2) + " " + humano2.getNombre() +
                            " SE ORINO !!! EL GANADOR ES EL " + getNombreRaza(humano1) + " "
                            + humano1.getNombre() + " !!!" + App.ANSI_RESET, App.DEFAULT_DELAY);
                    ganador = humano1;
                }
            }
        } while (Objects.isNull(ganador) && puntosHumano1 < max_cervezas && puntosHumano2 < max_cervezas) ;

        verificarGanador();
    }



    private void registrarGanador() throws SQLException {
        if (Objects.nonNull(ganador)) {
            if (ganador instanceof Vikingo) {
                Resultado resultado = new Resultado(ganador.getNombre(), puntosHumano1);
                resultado.registrarResultado();
            } else if (ganador instanceof Espartano) {
                Resultado resultado = new Resultado(ganador.getNombre(), puntosHumano2);
                resultado.registrarResultado();
            }
        }
    }

    public Humano getGanador() {
        return ganador;
    }

    private String getNombreRaza(Humano h) {
        if(h instanceof Vikingo) return "VIKINGO";
        else if(h instanceof Espartano) return "ESPARTANO";
        else if(h instanceof FinalBoss) return "DUEÑO DE LA TABERNA";
        else return "INDEFINIDO";
    }
}
