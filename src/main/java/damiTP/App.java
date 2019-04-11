package damiTP;

import damiTP.Config.ConnectionMySQL;
import damiTP.Models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

        List<Humano> vikingos = new ArrayList<>(Arrays.asList(
                new Vikingo("Egil", 23, new OrinarVikingoImp(),new BeberVikingoImp(),5),
                new Vikingo("Daven", 33, new OrinarVikingoImp(),new BeberVikingoImp(),3),
                new Vikingo("Aren", 26, new OrinarVikingoImp(),new BeberVikingoImp(),2)
        ));

        List<Humano> espartanos = new ArrayList<>(Arrays.asList(
                new Espartano("Chris" , 19 , new OrinarEspartanoImp(), new BeberEspartanoImp(), 5),
                new Espartano("Kratos" , 40 , new OrinarEspartanoImp(), new BeberEspartanoImp(), 7),
                new Espartano("ADONIS" , 32 , new OrinarEspartanoImp(), new BeberEspartanoImp(), 4)
        ));

        //DNI PAR. POR LO QUE SE ORDENA LA LISTA POR EDAD

        vikingos.sort(Comparator.comparingInt(Humano::getEdad));
        espartanos.sort(Comparator.comparingInt(Humano::getEdad));

        

        Torneo torneo = new Torneo(vikingos,espartanos);
        torneo.comenzar();

    }
}
