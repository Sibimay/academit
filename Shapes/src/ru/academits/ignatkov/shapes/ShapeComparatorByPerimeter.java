package ru.academits.ignatkov.shapes;

import java.util.Comparator;

public class ShapeComparatorByPerimeter implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getPerimeter(), o2.getPerimeter());
    }
}
