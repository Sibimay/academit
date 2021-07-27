package ru.academits.ignatkov.matrix;

import ru.academits.ignatkov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] elements;

    public Matrix(int m, int n) {
        if (m <= 0) {
            throw new IllegalArgumentException("Количетсво строк матрицы не может быть меньше 1. Сейчас строк " + m);
        }

        if (n <= 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы не может быть меньше 1. Сейчас столбцов " + n);
        }

        elements = new Vector[m];

        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Vector(n);
        }
    }

    public Matrix(Matrix matrix) {
        elements = Arrays.copyOf(matrix.elements, matrix.elements.length);
    }

    public Matrix(double[][] elements) {
        if (elements.length == 0) {
            throw new IllegalArgumentException("Число элементов не может быть равно 0");
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


    //todo don't like it
    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Число элементов не может быть равно 0");
        }

        int maxSize = vectors[0].getSize();

        for (Vector vector : vectors) {
            int vectorSize = vector.getSize();

            if (vectorSize > maxSize) {
                maxSize = vectorSize;
            }
        }

        elements = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            elements[i] = new Vector(maxSize);

            int vectorSize = vectors[i].getSize();

            for (int j = 0; j < maxSize; j++) {
                if (vectorSize < j + 1) {
                    continue;
                }

                elements[i].setComponent(j, vectors[i].getComponent(j));
            }
        }
    }

    public int getRowsCount() {
        return elements.length;
    }

    public int getColumnsCount() {
        return elements[0].getSize();
    }

    public Vector getRowByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0. Индекс = " + index);
        }

        if (index > elements.length - 1) {
            throw new IndexOutOfBoundsException("Размер матрицы меньше, чем указанный индекс. Индекс = " + index);
        }

        return elements[index];
    }

    public void setRowByIndex(int index, Vector vector) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0. Индекс = " + index);
        }

        if (index > elements.length - 1) {
            throw new IndexOutOfBoundsException("Размер матрицы меньше, чем указанный индекс. Индекс = " + index);
        }

        int vectorSize = vector.getSize();

        if (vectorSize > elements[0].getSize()) {
            throw new IllegalArgumentException("Размер введенного вектора больше, чем число столбцов матрицы." +
                    " Размер вектора = " + vectorSize);
        }

        Vector row = elements[index];

        for (int i = 0; i < row.getSize(); i++) {
            double vectorComponent = vector.getComponent(i);
            row.setComponent(i, vectorComponent);
        }
    }

    public Vector getColumnByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0. Индекс = " + index);
        }

        int columnCount = elements[0].getSize() - 1;

        if (index > columnCount) {
            throw new IndexOutOfBoundsException("Размер матрицы меньше, чем указанный индекс. Индекс = " + index);
        }

        Vector resultVector = new Vector(columnCount);

        for (int i = 0; i < elements.length; i++) {
            Vector row = getRowByIndex(i);
            resultVector.setComponent(i, row.getComponent(index));
        }

        return resultVector;
    }

    public void transpose() {

    }

    @Override
    public String toString() {
        return Arrays.toString(elements).replace("[", "{").replace("]", "}");
    }
}
