package ru.anfy.java3.lesson5;


import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final int CARS_COUNT = 4;
    public static ExecutorService pool = Executors.newFixedThreadPool(CARS_COUNT);

    public static void main(String[] args) {
        try {
            doRace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void doRace() throws BrokenBarrierException, InterruptedException {

        AtomicInteger counter = new AtomicInteger(0);
        Car[] finishList = new Car[CARS_COUNT];

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(100, (int) CARS_COUNT / 2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT + 1);
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), barrier, counter, finishList);
        }
        for (int i = 0; i < cars.length; i++) {
            pool.execute(cars[i]);
        }

        barrier.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        barrier.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        for (int i = 0; i < CARS_COUNT; i++) {
            System.out.printf("Место %2d: %s\n",i + 1, finishList[i]);
        }

        pool.shutdown();

    }


}

