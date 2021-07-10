package ru.academits.ignatkov.shapes_main;

import ru.academits.ignatkov.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{new Circle(5), new Rectangle(2, 3), new Square(3),
                new Triangle(2, 3, 1, -7, 2, -4), new Rectangle(4, 1),
                new Triangle(1, 7, 4, 1, 3, 7), new Circle(3), new Square(7)
        };

        Shape shapeWithMaxArea = getShapeWithMaxArea(shapes);
        System.out.printf("Фигура с максимальной площадью: %s. Её площадь %f%n",
                shapeWithMaxArea, shapeWithMaxArea.getArea());

        Shape shapeWithSecondPerimeter = getShapeWithSecondPerimeter(shapes);
        System.out.printf("Фигура со вторым по величине периметром: %s. Её периметр %f",
                shapeWithSecondPerimeter, shapeWithSecondPerimeter.getPerimeter());
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        // Сделал через компаратор, среда разработки показала warning и предложила через лямбду
        Arrays.sort(shapes, (o1, o2) -> (int) (o1.getArea() - o2.getArea()));

        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, (o1, o2) -> (int) (o1.getPerimeter() - o2.getPerimeter()));

        return shapes[shapes.length - 2];
    }
}
