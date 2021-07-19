package ru.academits.ignatkov.shapes_main;

import ru.academits.ignatkov.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(5),
                new Rectangle(2, 3),
                new Square(3),
                new Triangle(2, 3, 1, -7, 2, -4),
                new Rectangle(4, 1),
                new Triangle(1, 7, 4, 1, 3, 7),
                new Circle(3),
                new Square(7)
        };

        Shape shapeWithMaxArea = getShapeWithMaxArea(shapes);
        if (shapeWithMaxArea != null) {
            System.out.printf("Фигура с максимальной площадью: %s. Её площадь %f%n",
                    shapeWithMaxArea, shapeWithMaxArea.getArea());
        } else {
            System.out.println("Нельзя найти максимальную по площади фигуру в пустом массиве");
        }

        Shape shapeWithSecondPerimeter = getShapeWithSecondPerimeter(shapes);
        if (shapeWithSecondPerimeter != null) {
            System.out.printf("Фигура со вторым по величине периметром: %s. Её периметр %f",
                    shapeWithSecondPerimeter, shapeWithSecondPerimeter.getPerimeter());
        } else {
            System.out.println("Нельзя найти вторую максимальную по периметру фигуру, если в массиве меньше двух фигур");
        }
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        if (shapes.length <= 0) {
            return null;
        }

        if (shapes.length == 1) {
            return shapes[0];
        }

        Arrays.sort(shapes, new ShapeByAreaComparator());

        return shapes[shapes.length - 1];

    }

    public static Shape getShapeWithSecondPerimeter(Shape[] shapes) {
        if (shapes.length <= 1) {
            return null;
        }

        if (shapes.length == 2) {
            if (shapes[0].getPerimeter() > shapes[1].getPerimeter()) {
                return shapes[1];
            }

            return shapes[0];
        }

        Arrays.sort(shapes, new ShapeByPerimeterComparator());

        return shapes[shapes.length - 2];
    }
}
