package ru.academits.ignatkov.shapes;

import java.util.Comparator;

public class ShapeComparatorByArea implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getArea(), o2.getArea());
    }
}
