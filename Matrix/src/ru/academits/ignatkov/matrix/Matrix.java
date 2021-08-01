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

        for (int i = 0; i < elements.length - 1; i++) {
            Vector row = getRowByIndex(i);
            resultVector.setComponent(i, row.getComponent(index));
        }

        return resultVector;
    }

    public void transpose() {
        Vector[] resultVector = new Vector[elements[0].getSize()];

        int columnsCount = elements[0].getSize();

        for (int i = 0; i < columnsCount; i++) {
            resultVector[i] = new Vector(elements.length);

            for (int j = 0; j < elements.length; j++) {
                resultVector[i].setComponent(j, elements[j].getComponent(i));
            }
        }

        elements = Arrays.copyOf(resultVector, columnsCount);
    }

    public void multiplyByScalar(double number) {
        for (Vector element : elements) {
            element.multiplyByScalar(number);
        }
    }

    public double getDeterminant() {
        int rowsCount = elements.length;
        int columnsCount = elements[0].getSize();

        if (columnsCount == 1) {
            return elements[0].getComponent(0);
        }

        if (columnsCount == 2) {
            return elements[0].getComponent(0) * elements[1].getComponent(1) - elements[0].getComponent(1) * elements[1].getComponent(0);
        }

        double det = 0;

        for (int i = 0; i < rowsCount; i++) {
            double[][] algebraicComplement = new double[rowsCount - 1][columnsCount - 1];

            for (int j = 1; j < rowsCount; j++) {
                int complementColumnsCount = 0;

                for (int k = 0; k < columnsCount; k++) {
                    if (k == i) {
                        continue;
                    }

                    algebraicComplement[j - 1][complementColumnsCount] = elements[j].getComponent(k);
                    complementColumnsCount++;
                }
            }

            det += elements[0].getComponent(i) * (new Matrix(algebraicComplement)).getDeterminant() * Math.pow(-1, i);
        }

        return det;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements).replace("[", "{").replace("]", "}");
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Длина вектора должна равняться числу столбцов в матрице!");
        }

        double[] resultVector = new double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            resultVector[i] = Vector.getScalarProduct(elements[i], vector);
        }

        return new Vector(resultVector);
    }

    public void add(Matrix matrix1) {
        if (getColumnsCount() != matrix1.getColumnsCount() || elements.length != matrix1.elements.length) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }

        for (int i = 0; i < elements.length; i++) {
            elements[i].add(matrix1.elements[i]);
        }
    }

    public void subtract(Matrix matrix1) {
        if (getColumnsCount() != matrix1.getColumnsCount() || elements.length != matrix1.elements.length) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }

        for (int i = 0; i < elements.length; i++) {
            elements[i].subtract(matrix1.elements[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount() || matrix1.elements.length != matrix2.elements.length) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.add(matrix2);

        return matrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount() || matrix1.elements.length != matrix2.elements.length) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }

        Matrix matrix = new Matrix(matrix1);
        matrix.subtract(matrix2);

        return matrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int matrix1ColumnsCount = matrix1.getColumnsCount();

        if (matrix1ColumnsCount != matrix2.elements.length) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равны кол-ву строк втрой матрицы!");
        }

        int matrix2ColumnsCount = matrix2.getColumnsCount();

        double[][] resultElements = new double[matrix1.elements.length][matrix2ColumnsCount];

        for (int i = 0; i < matrix1.elements.length; i++) {
            for (int j = 0; j < matrix2ColumnsCount; j++) {
                for (int k = 0; k < matrix1ColumnsCount; k++) {
                    resultElements[i][j] += matrix1.elements[i].getComponent(k) * matrix2.elements[k].getComponent(j);
                }
            }
        }

        return new Matrix(resultElements);
    }
}
