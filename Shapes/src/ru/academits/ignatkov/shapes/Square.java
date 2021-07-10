package ru.academits.ignatkov.shapes;

import java.util.Objects;

public class Square implements Shape {
    private final double height;

    public Square(double height) {
        this.height = height;
    }

    @Override
    public double getWidth() {
        return height;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return height * height;
    }

    @Override
    public double getPerimeter() {
        return height * 4;
    }

    @Override
    public String toString() {
        return "Square {height=" + height + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;

        return Double.compare(square.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height);
    }
}
