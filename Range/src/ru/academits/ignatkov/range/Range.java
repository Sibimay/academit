package ru.academits.ignatkov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double point) {
        return point >= from && to >= point;
    }

    public Range getIntersection(Range range) {
        if (to <= range.from || from >= range.to) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (range.from <= from && range.to >= from) {
            return new Range[]{new Range(range.from, Math.max(range.to, to))};
        }

        if (range.from >= from && to >= range.from) {
            return new Range[]{new Range(from, Math.max(range.to, to))};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (from < range.from && to >= range.from) {
            return new Range[]{new Range(from, range.from)};
        }

        if (range.from >= to || from >= range.to) {
            return new Range[]{new Range(from, to)};
        }

        if (range.from <= from) {
            return new Range[]{};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    @Override
    public String toString() {
        return String.format("(%f; %f)%n", from, to);
    }
}
