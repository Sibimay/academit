package ru.academits.ignatkov.range;

public class Range {
    private double from;
    private double to;

    Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double point) {
        return point - from >= 0 && to - point >= 0;
    }

    public Range getIntersectionRange(Range anotherRange) {
        double anotherRangeFrom = anotherRange.getFrom();
        double anotherRangeTo = anotherRange.getTo();
        double intersectionRangeFromNumber;
        double intersectionRangeToNumber;

        if (to == anotherRangeFrom || to < anotherRangeFrom || from > anotherRangeTo) {
            return null;
        }

        if (this.isInside(anotherRangeFrom) && this.isInside(anotherRangeTo)) {
            intersectionRangeFromNumber = anotherRangeFrom;
            intersectionRangeToNumber = anotherRangeTo;
        } else if (this.isInside(anotherRangeFrom)) {
            intersectionRangeFromNumber = anotherRangeFrom;
            intersectionRangeToNumber = to;
        } else if (this.isInside(anotherRangeTo)) {
            intersectionRangeFromNumber = from;
            intersectionRangeToNumber = anotherRangeTo;
        } else {
            intersectionRangeFromNumber = from;
            intersectionRangeToNumber = to;
        }

        return new Range(intersectionRangeFromNumber, intersectionRangeToNumber);
    }

    public Range[] getCombineRanges(Range anotherRange) {
        double anotherRangeFrom = anotherRange.getFrom();
        double anotherRangeTo = anotherRange.getTo();
        Range[] ranges = new Range[2];

        if (this.isInside(anotherRangeFrom) && this.isInside(anotherRangeTo)) {
            ranges[0] = new Range(from, to);
        } else if (anotherRange.isInside(from) && anotherRange.isInside(to)) {
            ranges[0] = new Range(anotherRangeFrom, anotherRangeTo);
        } else if (this.isInside(anotherRangeFrom)) {
            ranges[0] = new Range(from, anotherRangeTo);
        } else if (anotherRange.isInside(from)) {
            ranges[0] = new Range(anotherRangeFrom, to);
        } else {
            ranges[0] = this;
            ranges[1] = anotherRange;
        }

        return ranges;
    }

    @Override
    public String toString() {
        return "[" + getFrom() + ", " + getTo() + "]";
    }
}
