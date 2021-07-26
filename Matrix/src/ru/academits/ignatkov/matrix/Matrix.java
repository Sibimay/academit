package ru.academits.ignatkov.matrix;

import ru.academits.ignatkov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private int columnCount;
    private int rowCount;
    private Vector[] elements;

    public Matrix(int m, int n) {
        if (m <= 0) {
            throw new IllegalArgumentException("Количетсво строк матрицы не может быть меньше 1. Сейчас строк " + m);
        }

        if (n <= 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы не может быть меньше 1. Сейчас столбцов " + n);
        }

        rowCount = m;
        columnCount = n;

        elements = new Vector[m];

        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Vector(n);
        }
    }

    public Matrix (Matrix matrix) {
        elements = matrix.elements;
    }

    public Matrix(double[][] elements) {
        if (elements.length <= 0) {
            throw new IllegalArgumentException("Один или оба размера матрицы не могут быть меньше 1");
        }

        int maxSize = elements.length;

        for (double[] element : elements) {
            if (element.length > maxSize) {
                maxSize = element.length;
            }
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i].length < maxSize)
            elements[i] = Arrays.copyOf(elements[i], maxSize);
        }

        this.elements = new Vector[elements.length];

        for (int i = 0; i < elements.length; i++) {
            this.elements[i] = new Vector(elements[i]);
        }
    }

    public Matrix (Vector[] vectors) {
        elements = vectors;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public Vector[] getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements).replace("[", "{").replace("]", "}");
    }
}
