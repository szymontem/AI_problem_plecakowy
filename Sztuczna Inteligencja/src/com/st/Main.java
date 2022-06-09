package com.st;

import java.io.File;
import java.text.CollationElementIterator;
import java.util.*;

public class Main {

    static String path = "D:\\JavaProjekty\\Sztuczna Inteligencja\\out\\production\\Sztuczna Inteligencja\\com\\st\\movies.txt";

    static int moneyLimit = 1000;

    public static void main(String[] args) {

        try {
            File f = new File(path);
            Scanner sc = new Scanner(f);
            LinkedList<Movie> allMovies = new LinkedList<>();
            Random r = new Random();

            while(sc.hasNextLine()){
                allMovies.add(new Movie(sc.nextLine()));
            }

            //movies.forEach((Movie m) -> {System.out.println(m + " dur: "+m.duration);});

            LinkedList<Movie> movies = new LinkedList<>(allMovies);
            LinkedList<Backpack> bps= new LinkedList<>();

            //Stworz 10 losowych plecakow
            for (int i = 0; i < 10; i++) {
                Backpack bp = new Backpack();
                while (true) {
                    int index = r.nextInt(movies.size());
                    bp.addMovie(movies.get(index));
                    if (bp.getValue() > moneyLimit) {
                        bp.removeMovie(movies.get(index));
                        break;
                    }
                    movies.remove(movies.get(index));
                }
                bps.add(bp);
                movies.clear();
                movies.addAll(allMovies);
            }

            Collections.sort(bps);
            System.out.println(bps);

            for (int i = 0; i < 10000000; i++) {
                int hmcr=70;
                int random = r.nextInt(101);
                //Jesli trafilo stworz nowy losowy plecak
                if(random<hmcr){
                    Backpack bp = new Backpack();
                    while (true) {
                        int index = r.nextInt(movies.size());
                        bp.addMovie(movies.get(index));
                        if (bp.getValue() > moneyLimit) {
                            bp.removeMovie(movies.get(index));
                            break;
                        }
                        movies.remove(movies.get(index));
                    }
                    movies.clear();
                    movies.addAll(allMovies);

                    //i wrzuc go do zbioru, wczesniej wyrzuc pierwszy napotkany gorszy plecak
                    for (int j = 0; j < 10; j++) {
                        if(bps.get(j).totalTime < bp.totalTime){
                            System.out.println("Backpack with total time "+bp.totalTime+"ms"+" beats backpack with total time "+bps.get(j).totalTime+"ms. Removing...");
                            bps.remove(j);
                            bps.add(bp);
                            break;
                        }
                    }
                }
                //jesli nie trafiło
                else{
                    System.out.println("HMCR "+random);
                    Backpack bp = new Backpack();

                    for (int j = 0; j < 20; j++) {
                        int number = r.nextInt(10);
                        bp.addMovie(bps.get(number).movies.get(j));
                        bp.setValue();
                    }

                    System.out.println(bp.totalTime);
                    System.out.println(bp.getValue());

                    for (int j = 0; j < 10; j++) {
                        if(bps.get(j).totalTime < bp.totalTime){
                            System.out.println("Backpack with total time "+bp.totalTime+"ms"+" beats backpack with total time "+bps.get(j).totalTime+"ms. Removing...");
                            bps.remove(j);
                            bps.add(bp);
                            break;
                        }
                    }
                }
                double compleed = (double)i/10000000;
                System.out.println("COMPLETED: "+compleed);
            }

            Collections.sort(bps);
            System.out.println("10 najlepszych plecaków: \n"+bps);

            System.out.println("Najlepszy plecak: \n"+bps.get(9));


        }catch (Exception e){e.printStackTrace();}
    }
}
