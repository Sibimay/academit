package ru.academits.ignatkov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0. size = " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Длина массива должна быть больше 0. Переданная длина массива = " + components.length);
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0. size = " + size);
        }

        this.components = Arrays.copyOf(components, size);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(components).replace("[", "{").replace("]", "}");
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        } else if (vector.components.length < components.length) {
            vector.components = Arrays.copyOf(vector.components, components.length);
        }

        for (int i = 0; i < components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        } else if (vector.components.length < components.length) {
            vector.components = Arrays.copyOf(vector.components, components.length);
        }

        for (int i = 0; i < components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double componentsSquaresSum = 0;

        for (double component : components) {
            componentsSquaresSum += component * component;
        }

        return Math.sqrt(componentsSquaresSum);
    }

    public double getComponent(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным. Индекс = " + index);
        }

        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше, чем размерность вектора. " +
                    "Индекс = " + index + ", размерность вектора = " + components.length);
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным. Индекс = " + index);
        }

        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше, чем размерность вектора. " +
                    "Индекс = " + index + ", размерность вектора = " + components.length);
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

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector sumVector = new Vector(vector1);
        sumVector.add(vector2);

        return sumVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector differenceVector = new Vector(vector1);
        differenceVector.subtract(vector2);

        return differenceVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        int minVectorSize = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minVectorSize; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
