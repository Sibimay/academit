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
        if (to == range.from || from == range.to || to < range.from || from > range.to) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if ((range.isInside(from) || range.isInside(to)) || (this.isInside(range.from) || this.isInside(range.to))) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    public Range[] getDifference(Range range) {
        if (from == range.from && to == range.to) {
            return new Range[]{};
        }

        if (range.isInside(from) && range.isInside(to)) {
            return new Range[]{new Range(from, to)};
        }

        if (this.isInside(range.from) && to == range.to) {
            return new Range[]{new Range(from, range.from)};
        }

        if (this.isInside(range.to) && from == range.from) {
            return new Range[]{new Range(range.to, to)};
        }

        if (this.isInside(range.from) && this.isInside(range.to)) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (this.isInside(range.from)) {
            return new Range[]{new Range(from, range.from), new Range(to, range.to)};
        }

        if (this.isInside(range.to)) {
            return new Range[]{new Range(range.from, from), new Range(range.to, to)};
        }

        return new Range[]{new Range(from, to), new Range(range.from, range.to)};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}
