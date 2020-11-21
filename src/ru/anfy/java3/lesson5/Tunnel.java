package ru.anfy.java3.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    Semaphore tunnelSemaphore;

    public Tunnel(int width) {
        setDefaults();
        setDescription();
    }

    public Tunnel(int length, int width) {
        setDefaults(); //Это тут не нужно конечно, но типа для масштабирования, мало-ли какие ещё параметры будут
        this.length = length;
        this.tunnelSemaphore = new Semaphore(width); //Второй раз переопределяем объект, что не сделаешь ради красивого кода
        setDescription();
    }

    public void setDefaults(){
        this.length = 80;
        this.tunnelSemaphore = new Semaphore(1); //Тоннель на одну машинку по умолчанию
    }

    private void setDescription(){
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                tunnelSemaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                tunnelSemaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
