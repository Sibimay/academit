package ru.academits.ignatkov.shapes_main;

import ru.academits.ignatkov.shapes.Shape;

import java.util.Comparator;

public class ShapeByPerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.getPerimeter(), s2.getPerimeter());
    }
}
