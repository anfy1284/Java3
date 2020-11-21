package ru.anfy.java3.lesson5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier barrier;
    private int finishNumber;
    private AtomicInteger counter;
    private Car[] finishList;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier barrier, AtomicInteger counter, Car[] finishList) {
        this.race = race;
        this.speed = speed;
        this.barrier = barrier;
        this.counter = counter;
        this.finishList = finishList;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            barrier.await();
            Thread.sleep(1);

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            finishList[counter.getAndIncrement()] = this;
            barrier.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
