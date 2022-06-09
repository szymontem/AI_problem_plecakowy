package com.st;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

public class Backpack implements Comparable{
    private static long ID;
    public long id;
    public LinkedList<Movie> movies = new LinkedList<>();
    private int value;
    public long totalTime;
    public Duration time;

    public Backpack() {
        this.id=ID++;
    }

    public void addMovie(Movie m){
        this.movies.add(m);
        this.setTotalTime();
        this.setValue();
        this.setTime();
    }

    public void removeMovie(Movie m){
        this.movies.remove(m);
        this.setTotalTime();
        this.setValue();
        this.setTime();
    }

    public void setValue(){
        this.value=0;
        movies.forEach((Movie m) -> {
            this.value+=m.ticketCost;
        });
    }

    public void setTotalTime(){
        this.totalTime=0;
        movies.forEach((Movie m) -> {
            this.totalTime+=m.duration;
        });
    }

    public String toString(){
        return "Backpack "+this.id+": "+
                "\n Movies: "+this.movies.size()+
                "\n Total cost: "+this.value+
                "\n Total time: "+this.totalTime+" ("+
                String.format("%d:%02d:%02d", this.time.getSeconds() / 3600, (this.time.getSeconds() % 3600) / 60, (this.time.getSeconds() % 60))
                +")";
    }

    public int getValue() {
        return value;
    }

    public void setTime() {
        this.time = Duration.ofSeconds(this.totalTime);
    }

    @Override
    public int compareTo(Object o) {
        return (this.totalTime>((Backpack) o).totalTime)?1:-1;
    }
}
