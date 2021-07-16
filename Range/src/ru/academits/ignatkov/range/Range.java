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
        if (to < range.from || range.to < from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (to <= range.from || range.to <= from) {
            return new Range[]{new Range(from, to)};
        }

        double minTo = Math.min(to, range.to);
        double maxFrom = Math.max(from, range.from);

        if (maxFrom == from && minTo == to) {
            return new Range[]{};
        }

        if (maxFrom == from) {
            return new Range[]{new Range(minTo, to)};
        }

        if (minTo == to) {
            return new Range[]{new Range(from, maxFrom)};
        }

        return new Range[]{new Range(from, maxFrom), new Range(minTo, to)};
    }

    @Override
    public String toString() {
        return String.format("(%f; %f)", from, to);
    }
}
