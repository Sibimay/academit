package ru.academits.ignatkov.arraylisthome;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Подзадача 1
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("ArrayListHome/src/ru/academits/ignatkov/arraylisthome/input.txt"))) {
            ArrayList<String> fileLines = new ArrayList<>();

            String line = bufferedReader.readLine();

            while (line != null) {
                fileLines.add(line);
                line = bufferedReader.readLine();
            }

            System.out.println("Строки, прочитанные из файла: " + fileLines);
        } catch (FileNotFoundException e) {
            System.out.println("Входной файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }

        // Подзадача 2
        ArrayList<Integer> arrayList1 = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10));

        System.out.println("Список до удаления чётных чисел: " + arrayList1);

        Iterator<Integer> iterator = arrayList1.iterator();

        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println("Список после удаления чётных чисел: " + arrayList1);

        // Подзадача 3
        ArrayList<Integer> arrayList3 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 2, 7));
        ArrayList<Integer> arrayListWithoutRepeats = new ArrayList<>(arrayList3.size());

        for (Integer element : arrayList3) {
            if (!arrayListWithoutRepeats.contains(element)) {
                arrayListWithoutRepeats.add(element);
            }
        }

        System.out.println("Список без повторяющихся чисел" + arrayListWithoutRepeats);
    }
}
