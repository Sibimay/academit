package ru.academits.ignatkov.range_main;

import ru.academits.ignatkov.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class RangeTest {
    public static void main(String[] args) {
        // Range часть 1
        double from1 = 1.2;
        double to1 = 5.3;

        Range range1 = new Range(from1, to1);

        System.out.println("Начальная точка диапазона = " + range1.getFrom());
        System.out.println("Конечная точка диапазона = " + range1.getTo());

        System.out.println("Длина диапазона - " + range1.getLength());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число, чтобы проверить, попадает ли оно в диапазон:");
        double enteredNumber = scanner.nextDouble();

        System.out.printf("Введенное число %f " + (range1.isInside(enteredNumber) ? "" : "не ") + "в диапазоне от %f до %f%n",
                enteredNumber, from1, to1);

        double changedRangeFrom = -1.4;
        double changedRangeTo = 10.23;
        range1.setFrom(changedRangeFrom);
        range1.setTo(changedRangeTo);

        System.out.printf("Границы диапазона изменились. Число %f " + (range1.isInside(enteredNumber) ? "" : "не ")
                + "попадает в диапазон от %f до %f%n", enteredNumber, changedRangeFrom, changedRangeTo);

        // Range часть 2
        Range range2 = new Range(3, 7);
        Range range3 = new Range(1, 5);

        printRangesIntersection(range2, range3);
        printRangesUnion(range2, range3);
        printRangesDifference(range2, range3);
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
        Range[] rangesDifference = range1.getDifference(range2);

        if (rangesDifference.length == 0) {
            System.out.println("После вычитания интервалов нет");
        } else if (rangesDifference.length == 1) {
            System.out.println("После вычитания получился интервал: " + rangesDifference[0]);
        } else {
            System.out.println("После вычитания получились интервалы: " + Arrays.toString(rangesDifference));
        }
    }
}
