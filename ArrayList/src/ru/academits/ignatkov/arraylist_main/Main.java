package ru.academits.ignatkov.arraylist_main;

import ru.academits.ignatkov.arraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>(6);

        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);

        System.out.println(myArrayList);
        System.out.println(myArrayList.size());
        myArrayList.addAll(1, new ArrayList<>(Arrays.asList(5, 6, 7, 8)));

        System.out.println(myArrayList);
    }
}
