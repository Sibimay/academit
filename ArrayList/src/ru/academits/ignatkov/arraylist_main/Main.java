package ru.academits.ignatkov.arraylist_main;

import ru.academits.ignatkov.arraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>(6);

        System.out.println("Is myArrayList empty: " + myArrayList.isEmpty());

        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        System.out.println("New array list: " + myArrayList + " and its size: " + myArrayList.size());
        System.out.println("Is myArrayList empty: " + myArrayList.isEmpty());

        myArrayList.addAll(1, new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
        System.out.println("After 'Add all' array list is: " + myArrayList);

        myArrayList.remove(Integer.valueOf(2));
        System.out.println("After 'Remove' of 2 array list is: " + myArrayList);

        myArrayList.add(0, 3);
        System.out.println("After 'Add by index' (index = 0, value = 3) array list is: " + myArrayList);

        System.out.println("Index of first 3 is: " + myArrayList.indexOf(3));
        System.out.println("Index of last 3 is: " + myArrayList.lastIndexOf(3));

        myArrayList.retainAll(new ArrayList<>(Arrays.asList(1, 2, 5, 10)));
        System.out.println("After 'Retain' array list is: " + myArrayList);

        myArrayList.clear();
        System.out.println("After 'clear' array list is: " + myArrayList);

        myArrayList.add(0, 17);
        myArrayList.add(1, 12);
        System.out.println("After 'add with index' array list is: " + myArrayList);

        System.out.println("Is array list contains 12: " + myArrayList.contains(12));
        System.out.println("Is array list contains 13: " + myArrayList.contains(13));
        System.out.println("Is array list contains 17 and 12: " + myArrayList.containsAll(new ArrayList<>(Arrays.asList(17, 12))));
        System.out.println("Is array list contains 12 and 17: " + myArrayList.containsAll(new ArrayList<>(Arrays.asList(12, 17))));
    }
}
