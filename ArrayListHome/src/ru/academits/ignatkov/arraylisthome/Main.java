package ru.academits.ignatkov.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Подзадача 1
        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/ru/academits/ignatkov/arraylisthome/input.txt"))) {
            ArrayList<String> arrayList1 = new ArrayList<>();

            while (scanner.hasNextLine()) {
                arrayList1.add(scanner.nextLine());
            }

            System.out.println(arrayList1);
        }

        // Подзадача 2
        ArrayList<Integer> arrayList2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        for (int i = 0; i < arrayList2.size(); i++) {
            Integer element = arrayList2.get(i);

            if (element % 2 == 0) {
                arrayList2.remove(element);
            }
        }

        System.out.println(arrayList2);

        // Подзадача 3
        ArrayList<Integer> arrayList3 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 2, 7));
        ArrayList<Integer> resultArrayList = new ArrayList<>();

        for (Integer element : arrayList3) {
            if (!resultArrayList.contains(element)) {
                resultArrayList.add(element);
            }
        }

        System.out.println(resultArrayList);
    }
}
