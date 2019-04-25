package com.javarush.task.task21.task2113;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses;


    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List horses){
        this.horses = horses;
    }


    public static void main(String[] args) throws Exception{
        List<Horse> horses = new ArrayList<>();
        game = new Hippodrome(horses);
        Horse horse = new Horse("Igor", 3.0,0);
        Horse horse1 = new Horse("Gnar", 3.0,0);
        Horse horse2 = new Horse("Zimmer", 3.0,0);
        horses.add(horse);
        horses.add(horse1);
        horses.add(horse2);
        game.run();
        game.printWinner();
    }

    public void run() throws InterruptedException {
        for (int i = 0; i<100; i++){
            move();
            Thread.sleep(200);
            print();
        }

    }
    public void move(){
        for (int i = 0; i<horses.size(); i++){
            horses.get(i).move();
        }
    }
    public void print(){
            for (int i = 0; i<horses.size(); i++){
                horses.get(i).print();
            }
            for (int i = 0; i<10; i++){
                System.out.println();
            }
        }
        public Horse getWinner(){
        Horse winner = horses.get(0);
        double disstance = horses.get(0).getDistance();
        for ( Horse winnerHorse : horses){
            if (winnerHorse.getDistance()>disstance) winner = winnerHorse;
        }
        return winner;
    }
        public void printWinner(){
            System.out.println("Winner is " + getWinner().getName() + "!");
        }
    }

