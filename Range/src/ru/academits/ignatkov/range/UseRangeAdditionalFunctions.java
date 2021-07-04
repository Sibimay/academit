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
        subtractRanges(range1, range2);
    }

    public static void intersectionRanges(Range range1, Range range2) {
        Range intersectionRange = range1.getIntersectionRange(range2);

        if (intersectionRange == null) {
            System.out.println("Интервалы не перескаются");
        } else {
            System.out.println("Границы интервала после пересечения " + intersectionRange);
        }
    }

    public static void combineRanges(Range range1, Range range2) {
        Range[] combinedRanges = range1.getCombineRanges(range2);

        if (combinedRanges[1] == null) {
            System.out.println("Интервал, полученный после объединиения: " + combinedRanges[0]);
        } else {
            System.out.println("Интервалы, полученный после объединиения: " + Arrays.toString(combinedRanges));
        }
    }

    public static void subtractRanges(Range range1, Range range2) {
        Range[] subtractedRanges = range1.getSubtractionRanges(range2);

        if (subtractedRanges[0] == null && subtractedRanges[1] == null) {
            System.out.println("После вычитания интервалов нет");
        } else if (subtractedRanges[1] == null) {
            System.out.println("После вычитания получился интервал: " + subtractedRanges[0]);
        } else {
            System.out.println("После вычитания получились интервалы: " + Arrays.toString(subtractedRanges));
        }
    }
}
