package ru.academits.ignatkov.range;

import java.util.Scanner;

public class UseRange {
    public static void main(String[] args) {
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
    }
}
