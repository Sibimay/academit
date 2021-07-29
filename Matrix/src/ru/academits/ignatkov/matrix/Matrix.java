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
        int rowsCount = this.elements.length;
        int columnsCount = elements[0].getSize();

        if (rowsCount != columnsCount) {
            throw new ArithmeticException("Нельзя вычислять определитель у прямоугольной матрицы. " +
                    "Размеры матрицы: " + rowsCount + "x" + columnsCount);
        }

        if (rowsCount <= 2) {
            System.out.println("HERE");
            return getRowByIndex(0).getComponent(0) * getRowByIndex(1).getComponent(1) -
                    getRowByIndex(0).getComponent(1) * getRowByIndex(1).getComponent(0);
        }

        int rowNumber = 0;
        int columnNumber = 0;
        Vector row = getRowByIndex(rowNumber);
        double rowSum = 0;

        for (int i = 0; i < row.getSize(); i++) {
            rowSum += row.getComponent(i);
        }

        if (rowSum == 0) {
            Vector column = getColumnByIndex(columnNumber);
            for (int i = 0; i < column.getSize(); i++) {
                rowSum += column.getComponent(i);
            }
        }


        System.out.println("row sum " + rowSum);
        double determinant = rowSum * getAlgebraicAddition(rowNumber, columnNumber).getDeterminant();

        return determinant;
    }

    private Matrix getAlgebraicAddition(int row, int column) {
        int size = elements.length;
        double[][] array = new double[size - 1][size - 1];

        int k;
        int n;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i >= row) {
                    k = i - 1;
                } else {
                    k = i;
                }

                if (j >= column) {
                    n = j - 1;
                } else {
                    n = j;
                }

                if (i == row || j == column) {
                    continue;
                }

                array[k][n] = getRowByIndex(i).getComponent(j);
            }
        }

        System.out.println("Array");
        for (double[] doubles : array) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println();

        return new Matrix(array);
    }


    @Override
    public String toString() {
        return Arrays.toString(elements).replace("[", "{").replace("]", "}");
    }
}
