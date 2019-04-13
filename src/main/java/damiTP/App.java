package damiTP;

import damiTP.Database.ResultadoDB;
import damiTP.Models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static final int RANDOM_GANAS_DE_ORINAR = 5;
    public static final int DEFAULT_DELAY = 800;

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
                new Vikingo("Egil", 23,6),
                new Vikingo("Daven", 33,4),
                new Vikingo("Aren", 26,2)
        ));

        List<Humano> espartanos = new ArrayList<>(Arrays.asList(
                new Espartano("Chris" , 19 , 5),
                new Espartano("Kratos" , 40 , 7),
                new Espartano("Adonis" , 32, 4)
        ));


        //DNI PAR. POR LO QUE SE ORDENA LA LISTA POR EDAD
        vikingos.sort(Comparator.comparingInt(Humano::getEdad));
        espartanos.sort(Comparator.comparingInt(Humano::getEdad));

        //INICIA EL TORNEO
        try{
            Torneo torneo = new Torneo(vikingos,espartanos);
            torneo.comenzar();
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }catch (ExceptionInInitializerError ex){
            System.out.println("ExceptionInInitializerError: " + ex.getMessage());
        }catch (NoClassDefFoundError e){
            System.out.println("NoClassDefFoundError: " + e.getMessage());
        }

        // SE OBTIENE LOS GANADORES DE CADA RONDA
        try {
            List<Resultado> resultados = ResultadoDB.getInstance().traerTodo();

            // SE MUESTAN LOS GANADORES
            System.out.println(ANSI_PURPLE + "\n\nGANADORES DE CADA RONDA: ");
            resultados.forEach(r -> {
                System.out.println("NOMBRE: " + r.getNombre_ganador() + " | PUNTOS DE CERVEZA: " + r.getPuntos_cerveza());
            });
            System.out.println(ANSI_RESET);
        }catch (NoClassDefFoundError e){
            System.out.println("NoClassDefFoundError: " + e.getMessage());
        }
    }
}
