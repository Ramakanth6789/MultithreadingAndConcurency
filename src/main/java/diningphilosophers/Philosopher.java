package diningphilosophers;

import java.util.*;

public class Philosopher implements Runnable {

    private int id;
    private volatile boolean full;
    private Chopstick leftChopStick;
    private Chopstick rightChopStick;
    private Random random;
    private int eatingCounter;

    public Philosopher(int id, Chopstick leftChopStick, Chopstick rightChopStick) {
        this.id = id;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
        this.random = new Random();
    }

    @Override
    public void run() {
        try{
            while (!full) {
                think();

                if (leftChopStick.pickUp(this, State.LEFT)) {
                    if (rightChopStick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopStick.putDown(this);
                    }
                    leftChopStick.putDown(this);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is eating..");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    @Override
    public String toString() {
        return "Philosopher " +id ;
    }
}