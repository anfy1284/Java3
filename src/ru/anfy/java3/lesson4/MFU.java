package ru.anfy.java3.lesson4;

public class MFU {

    private Object printer = new Object();
    private Object scanner = new Object();

    class printThread extends Thread {
        private Client client;

        public printThread(Client client) {
            this.client = client;
            start();
        }

        @Override
        public void run() {
            printSub(client);
        }
    }

    class scanThread extends Thread {
        private Client client;

        public scanThread(Client client) {
            this.client = client;
            start();
        }

        @Override
        public void run() {
            scanSub(client);
        }
    }

    public void print(Client client) {
        new printThread(client);
    }

    public void scan(Client client) {
        new scanThread(client);
    }

    public void copy(Client client) {
        System.out.println(client + " starts copy");
        try {
            new scanThread(client).join();
            new printThread(client).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(client + " finished copy");
    }

    private void printSub(Client client) {
        synchronized (printer) {
            System.out.println(client + " starts print");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(client + " finished print");
        }
    }

    private void scanSub(Client client) {
        synchronized (scanner) {
            System.out.println(client + " starts scan");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(client + " finished scan");
        }
    }

}
