package ru.academits.ignatkov.main;

import ru.academits.ignatkov.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class RangeTest {
    public static void main(String[] args) {
        // Range часть 1
        double from = 1.2;
        double to = 5.3;

        Range range = new Range(from, to);

        System.out.println("Начальная точка диапазона = " + range.getFrom());
        System.out.println("Конечная точка диапазона = " + range.getTo());

        System.out.println("Длина диапазона - " + range.getLength());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число, чтобы проверить, попадает ли оно в диапазон:");
        double enteredNumber = scanner.nextDouble();

        System.out.printf("Введенное число %f " + (range.isInside(enteredNumber) ? "" : "не ") + "в диапазоне от %f до %f%n",
                enteredNumber, from, to);

        double changedRangeFromNumber = -1.4;
        double changedRangeToNumber = 10.23;
        range.setFrom(changedRangeFromNumber);
        range.setTo(changedRangeToNumber);

        System.out.printf("Границы диапазона изменились. Число %f " + (range.isInside(enteredNumber) ? "" : "не ")
                + "попадает в диапазон от %f до %f%n", enteredNumber, changedRangeFromNumber, changedRangeToNumber);

        // Range часть 2
        double from1 = 2;
        double to1 = 3;
        Range range1 = new Range(from1, to1);

        double from2 = 1;
        double to2 = 6;
        Range range2 = new Range(from2, to2);

        printRangesIntersection(range1, range2);
        printRangesUnion(range1, range2);
        printRangesDifference(range1, range2);
    }

    public static void printRangesIntersection(Range range1, Range range2) {
        Range rangesIntersection = range1.getIntersection(range2);

        if (rangesIntersection == null) {
            System.out.println("Интервалы не перескаются");
        } else {
            System.out.println("Границы интервала после пересечения " + rangesIntersection);
        }
    }

    public static void printRangesUnion(Range range1, Range range2) {
        Range[] rangesUnion = range1.getUnion(range2);

        if (rangesUnion.length == 1) {
            System.out.println("Интервал, полученный после объединиения: " + rangesUnion[0]);
        } else {
            System.out.println("Интервалы, полученные после объединиения: " + Arrays.toString(rangesUnion));
        }
    }

    public static void printRangesDifference(Range range1, Range range2) {
        Range[] rangesDiffernce = range1.getDifference(range2);

        if (rangesDiffernce.length == 0) {
            System.out.println("После вычитания интервалов нет");
        } else if (rangesDiffernce.length == 1) {
            System.out.println("После вычитания получился интервал: " + rangesDiffernce[0]);
        } else {
            System.out.println("После вычитания получились интервалы: " + Arrays.toString(rangesDiffernce));
        }
    }
}
