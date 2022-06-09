package com.st;

import java.util.LinkedList;

public class Movie {
    public String title;
    public int ticketCost;
    public long duration;
    private String time;

    public Movie(String movieData) {
        this.title = movieData.split(",")[0];
        this.ticketCost = Integer.parseInt(movieData.split(",")[1]);
        this.duration = timeToLong(movieData.split(",")[2]);
        this.time = movieData.split(",")[2];
    }

    static Long timeToLong(String time){
        String[] s = time.split(":");
        LinkedList<Long> factors = new LinkedList<>();
        for(String t : s){
            factors.add(Long.parseLong(t));
        }

        return factors.get(2)
                + 60*factors.get(1)
                + 60*60*factors.get(0);

    }

    public String toString(){
        return this.title + "   " + this.ticketCost + "$" +"    "+this.time;
    }
}
