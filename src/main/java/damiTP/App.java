package damiTP;

import damiTP.Database.ResultadoDB;
import damiTP.Models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Hello world! - TP LAB 5 - TSSI - Damian Tacconi.
 *
 * EL JUEGO CONSISTE EN UN TORNEO DE VIKINGOS VS ESPARTANOS, EL DESAFIO RADICA EN TOMAR LA MAYOR
 * CANTIDAD DE CERVEZA POSIBLE SIN TENER QUE IR AL BAÑO A ORINAR. AMBOS BANDOS SE
 * ENFRENTARAN 1 a 1, EL HUMANO QUE TOME LA MAYOR CANTIDAD (50) DE CERVEZA (PUNTOS) GANA LA RONDA 1 vs 1.
 * SI EL HUMANO ORINA, PIERDE. SI AMBOS PASAN LOS 50 PUNTOS, GANA EL QUE MAS PUNTOS HIZO, EN CASO DE EMPATE
 * GANA EL VIKINGO.
 *
 * AL FINALIZAR TODAS LAS RONDAS, EL EQUIPO CON MAS VICTORIAS GANA, EN CASO DE EMPATE, GANAN LOS ESPARTANOS.
 *
 * EXTRA: LOS GANADORES DEL EQUIPO QUE OBTUVO LA VICTORIA, SE ENFRENTARAN AL DUEÑO DE LA TARBERNA, QUIEN ESTARA
 * DISPUESTO A ENFRENTARLOS, EL DUEÑO DE LA TABERNA BEBE COMO VIKINGO Y AGUANTA COMO ESPARTANO !!
 *
 */
public class App 
{

    public static final int RANDOM_GANAS_DE_ORINAR = 20;
    public static final int DEFAULT_DELAY = 1;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void comunicar(String s , int delay){
        System.out.println(s);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ){
        //INICIALIZO LOS EQUIPOS
        List<Humano> vikingos = new ArrayList<>(Arrays.asList(
                new Vikingo("Egil", 23,new OrinarVikingoImp(), new BeberVikingoImp(),6),
                new Vikingo("Daven", 33,new OrinarVikingoImp(), new BeberVikingoImp(),4),
                new Vikingo("Aren", 26,new OrinarVikingoImp(), new BeberVikingoImp(),2),
                new Vikingo("Viggo", 35,new OrinarVikingoImp(), new BeberVikingoImp(),6),
                new Vikingo("Sven", 40,new OrinarVikingoImp(), new BeberVikingoImp(),3),
                new Vikingo("Thor", 32,new OrinarVikingoImp(), new BeberVikingoImp(),5)
        ));

        List<Humano> espartanos = new ArrayList<>(Arrays.asList(
                new Espartano("Chris" , 19 ,new OrinarEspartanoImp(), new BeberEspartanoImp(), 5),
                new Espartano("Kratos" , 40 ,new OrinarEspartanoImp(), new BeberEspartanoImp(), 7),
                new Espartano("Adonis" , 32,new OrinarEspartanoImp(), new BeberEspartanoImp(), 4),
                new Espartano("Altair" , 37 ,new OrinarEspartanoImp(), new BeberEspartanoImp(), 6),
                new Espartano("Calisto" , 46 ,new OrinarEspartanoImp(), new BeberEspartanoImp(), 14),
                new Espartano("Eudor" , 54,new OrinarEspartanoImp(), new BeberEspartanoImp(), 5)
        ));


        //DNI PAR. POR LO QUE SE ORDENA LA LISTA POR EDAD
        vikingos.sort(Comparator.comparingInt(Humano::getEdad));
        espartanos.sort(Comparator.comparingInt(Humano::getEdad));

        //INICIA EL TORNEO
        try{
            Torneo torneo = new Torneo(vikingos,espartanos);
            List<Humano> ganadores = torneo.comenzar();

            // SE OBTIENE LOS GANADORES DE CADA RONDA

            List<Resultado> resultados = ResultadoDB.getInstance().traerTodo();

            // SE MUESTAN LOS NOMBRES Y PUNTOS DE LOS GANADORES
            System.out.println(ANSI_PURPLE + "\n\nGANADORES DE CADA RONDA: ");
            resultados.forEach(r ->
                System.out.println("NOMBRE: " + r.getNombre_ganador() + " | PUNTOS DE CERVEZA: "
                        + r.getPuntos_cerveza())
            );

            // SE MUESTRA QUE EQUIPO GANO
            System.out.println(ANSI_GREEN + "GANARON LOS " + torneo.getNombre_equipo_ganador()+ " !!" +ANSI_RESET);


            /*
            * COMIENZA LA BATALLA FINAL CONTRA EL DUEÑO DE LA TABERNA !
             */
            BatallaFinal batallaFinal = new BatallaFinal(
                    new FinalBoss("Mou",43,new OrinarEspartanoImp(),
                            new BeberEspartanoImp(),4,10),
                    ganadores);

            // SE OBTIENE AL GANADOR (EL DUEÑO DE LA TABERNA O EL VIKINGO/ESPARTANO QUE LO VENCIO
            Humano ganador = batallaFinal.comenzar();

            if(ganador instanceof FinalBoss) System.out.println(
                    ANSI_BLUE + "EL GANADOR ES EL DUEÑO DE LA TABERNA!!!" + ANSI_RESET);
            else if (ganador instanceof Vikingo) System.out.println( ANSI_BLUE
                    + "EL GANADOR ES EL VIKINGO " + ganador.getNombre() + ANSI_RESET);
            else if (ganador instanceof Espartano) System.out.println(ANSI_BLUE
                    + "EL GANADOR ES EL ESPARTANO " + ganador.getNombre() + ANSI_RESET);

        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }catch (ExceptionInInitializerError ex){
            System.out.println("ExceptionInInitializerError: " + ex.getMessage());
        }catch (NoClassDefFoundError e){
            System.out.println("NoClassDefFoundError: " + e.getMessage());
        }
    }
}
