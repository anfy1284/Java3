package ru.anfy.java3.lesson4;


class AbcPrint {
    String lastLetter = "C";

    private void tryPrint(String letter, String letterMustBefore) {
        while (lastLetter != letterMustBefore) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(letter);
        lastLetter = letter;
        notifyAll();
    }

    private synchronized void print(String letter, int count) {

        int counter = count;

        while (counter > 0) {
            switch (letter) {
                case "A":
                    tryPrint("A", "C");
                    break;
                case "B":
                    tryPrint("B", "A");
                    break;
                case "C":
                    tryPrint("C", "B");
                    break;
            }
            counter--;
        }

    }


    void print() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                print("A", 5);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                print("B", 5);
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                print("C", 5);
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


public class Main {

    public static void mfu(){

        final MFU mfu = new MFU();

        Client cl1 = new Client("Client 1");
        Client cl2 = new Client("Client 2");
        Client cl3 = new Client("Client 3");
        Client cl4 = new Client("Client 4");
        Client cl5 = new Client("Client 5");
        Client cl6 = new Client("Client 6");

        mfu.print(cl1);
        mfu.copy(cl2);
        mfu.scan(cl3);
        mfu.print(cl4);
        mfu.copy(cl5);
        mfu.print(cl6);

    }


    public static void main(String[] args) {
        //1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз
        // (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
        //notify - не применил
        new AbcPrint().print();
        System.out.println();

        //2. Создать MFU c функциями, сканирования, печати и ксерокопирования
        mfu();
    }
}
