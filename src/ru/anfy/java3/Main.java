package ru.anfy.java3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    //1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
    public static boolean change (Object[] arr, int e1, int e2){
        if(arr.length <= e1 || arr.length <= e2) return false;
        Object tmp = arr[e1];
        arr[e1] = arr[e2];
        arr[e2] = tmp;
        return true;
    }

    //2. Написать метод, который преобразует массив в ArrayList;
    public static <T> ArrayList<T> toArrayList(T ... arr){
        ArrayList<T> arrList = new ArrayList<T>();
        for (int i = 0; i < arr.length; i++) {
            arrList.add(arr[i]);
        }
        return arrList;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1,2,3,4,5,6,7,8};
        Float[] arr2 = {1.0f,2.0f,3.0f,4.0f,5.0f,6.0f,7.0f,8.0f};
        change(arr1, 1,3);
        change(arr2, 0,5);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        ArrayList<Integer> intArrList = toArrayList(arr1);
        System.out.println(intArrList.toString());

        Box<Apple> box1 = new Box<Apple>();
        Box<Apple> box2 = new Box<Apple>();
        Box<Orange> box3 = new Box<Orange>();

        box1.add(new Apple());
        box1.add(new Apple());

        box2.add(new Apple());
        box2.add(new Apple());

        box3.add(new Orange());
        box3.add(new Orange());

        System.out.println(box1.compare(box2));
        System.out.println(box1.compare(box3));

        box1.load(box2);


    }
}
