package ru.academits.ignatkov.range;

import java.util.Arrays;

public class UseRangeAdditionalFunctions {
    public static void main(String[] args) {
        double from1 = 0.1;
        double to1 = 1.7;
        Range range1 = new Range(from1, to1);

        double from2 = 0.3;
        double to2 = 2.5;
        Range range2 = new Range(from2, to2);

        intersectionRanges(range1, range2);
        combineRanges(range1, range2);
    }

    public static void intersectionRanges(Range range1, Range range2) {
        Range intersectionRange = range1.getIntersectionRange(range2);

        if (intersectionRange == null) {
            System.out.println("Интервалы не перескаются");
        } else {
            System.out.println("Границы нового интервала [" + intersectionRange.getFrom() + ", " + intersectionRange.getTo() + "]");
        }
    }

    public static void combineRanges(Range range1, Range range2) {
        System.out.println("Объединенные интервалы: " + Arrays.toString(range1.getCombineRanges(range2)));
    }
}
