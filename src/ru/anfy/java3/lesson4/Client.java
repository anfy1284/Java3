package ru.anfy.java3.lesson4;

public class Client {
    private String name;
    private MFU mfu;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void print(MFU mfu){

    }

    @Override
    public String toString() {
        return name;
    }
}
