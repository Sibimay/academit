package ru.academits.ignatkov.vector;

import java.util.Arrays;

public class Vector {
    private final int dimension;
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0");
        }

        dimension = n;
        components = new double[dimension];
        Arrays.fill(components, 0);
    }

    public Vector(Vector vector) {
        this(vector.dimension, vector.components);
    }

    public Vector(double[] components) {
        if (components.length <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0");
        }

        this.components = components;
        dimension = this.components.length;
    }

    public Vector(int n, double[] components) {
        if (n <= 0 || components.length <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0");
        }

        dimension = n;
        this.components = new double[dimension];

        for (int i = 0; i < n; i++) {
            this.components[i] = components.length - 1 >= i ? components[i] : 0;
        }
    }

    public int getSize() {
        return dimension;
    }

    public double[] getComponents() {
        return components;
    }

    @Override
    public String toString() {
        return Arrays.toString(components).replace("[", "{").replace("]", "}");
    }

    public void addVector(Vector vector) {
        int maxSize = Math.max(dimension, vector.dimension);

        if (dimension < maxSize) {
            components = Arrays.copyOf(components, vector.dimension);
        }

        for (int i = 0; i < maxSize; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtractVector(Vector vector) {
        int maxSize = Math.max(dimension, vector.dimension);

        if (dimension < maxSize) {
            components = Arrays.copyOf(components, vector.dimension);
        }

        for (int i = 0; i < maxSize; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multipleOnScalar(double number) {
        for (int i = 0; i < dimension; i++) {
            components[i] *= number;
        }
    }

    public void reverse() {
        multipleOnScalar(-1);
    }

    public double getComponent(int index) {
        if (index < 0 || index > dimension) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным " +
                    "и не может быть больше, чем размерность вектора");
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index > dimension) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным " +
                    "и не может быть больше, чем размерность вектора");
        }

        components[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return dimension == vector.dimension && Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        double componentsSum = 0;

        for (double component : components) {
            componentsSum += component;
        }

        return (int) (dimension * componentsSum);
    }

    public static Vector getVectorsSum(Vector vector1, Vector vector2) {
        Vector sumVector = new Vector(vector1);
        sumVector.addVector(vector2);

        return sumVector;
    }

    public static Vector getVectorsDifference(Vector vector1, Vector vector2) {
        Vector differenceVector = new Vector(vector1);
        differenceVector.subtractVector(vector2);

        return differenceVector;
    }

    public static Vector getVectorsScalarMultiplication(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.dimension, vector2.dimension);
        Vector resultVector = new Vector(maxSize);

        for (int i = 0; i < maxSize; i++) {
            if (vector1.dimension <= i || vector2.dimension <= i) {
                resultVector.components[i] = 0;
                continue;
            }

            resultVector.components[i] = vector1.components[i] * vector2.components[i];
        }

        return resultVector;
    }
}
