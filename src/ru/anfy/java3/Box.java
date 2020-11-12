package ru.anfy.java3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> items;

    Box(){
        items = new ArrayList<T>();
    }

    public void add(T item){
        items.add(item);
    }

    public float getWeight(){
        //в задании сказано умножить вес одного фрукта на количество,
        // но я всё-таки сделаю суммирование весов всех фруктов
        float sum = 0;
        for (Fruit item:items) {
            sum += item.getWeight();
        }
        return sum;
    }

    public boolean compare(Box externalBox){
        return getWeight() == externalBox.getWeight();
    }

    public void load(Box<T> externalBox){
        items.addAll(externalBox.items);
        externalBox.items.clear();
    }

}
