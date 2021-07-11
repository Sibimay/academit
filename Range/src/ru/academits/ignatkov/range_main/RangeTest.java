package ru.academits.ignatkov.range_main;

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

        double changedRangeFrom = -1.4;
        double changedRangeTo = 10.23;
        range.setFrom(changedRangeFrom);
        range.setTo(changedRangeTo);

        System.out.printf("Границы диапазона изменились. Число %f " + (range.isInside(enteredNumber) ? "" : "не ")
                + "попадает в диапазон от %f до %f%n", enteredNumber, changedRangeFrom, changedRangeTo);

        // Range часть 2
        double from1 = 1;
        double to1 = 5;
        Range range1 = new Range(from1, to1);

        double from2 = 3;
        double to2 = 7;
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
