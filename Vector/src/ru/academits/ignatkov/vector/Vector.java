package ru.academits.ignatkov.vector;

import java.util.Arrays;

public class Vector {
    private int vectorDimension;
    private final double[] vectorComponents;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        vectorDimension = n;
        vectorComponents = new double[vectorDimension];
        Arrays.fill(vectorComponents, 0);
    }

    public Vector(Vector vector) {
        this(vector.vectorDimension, vector.vectorComponents);
    }

    public Vector(double[] components) {
        vectorComponents = components;
    }

    public Vector(int n, double[] components) {
        vectorDimension = n;
        vectorComponents = new double[vectorDimension];

        for (int i = 0; i < n; i++) {
            vectorComponents[i] = components.length - 1 >= i ? components[i] : 0;
        }
    }

    public int getSize() {
        return vectorDimension;
    }

    @Override
    public String toString() {
        return Arrays.toString(vectorComponents).replace("[", "{").replace("]", "}");
    }
}
