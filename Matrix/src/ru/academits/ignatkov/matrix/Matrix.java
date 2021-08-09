package ru.academits.ignatkov.matrix;

import ru.academits.ignatkov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rows, int columns) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Количетсво строк матрицы не может быть меньше 1. Сейчас строк " + rows);
        }

        if (columns <= 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы не может быть меньше 1. Сейчас столбцов " + columns);
        }

        this.rows = new Vector[rows];

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = new Vector(columns);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Количество строк не может быть равно 0");
        }

        int maxSize = 0;

        for (double[] element : rows) {
            if (element.length > maxSize) {
                maxSize = element.length;
            }
        }

        if (maxSize == 0) {
            throw new IllegalArgumentException("Длина всех вложенных массивов равна 0");
        }

        this.rows = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(maxSize, rows[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Количество строк не может быть равно 0");
        }

        int maxSize = vectors[0].getSize();

        for (Vector vector : vectors) {
            int vectorSize = vector.getSize();

            if (vectorSize > maxSize) {
                maxSize = vectorSize;
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxSize);
            rows[i].add(vectors[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRowByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0. Индекс = " + index);
        }

        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Размер матрицы меньше, чем указанный индекс." +
                    " Индекс = " + index + ", размер матрицы " + rows.length);
        }

        return rows[index];
    }

    public void setRowByIndex(int index, Vector vector) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0. Индекс = " + index);
        }

        if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Размер матрицы меньше, чем указанный индекс." +
                    " Индекс = " + index + ", размер матрицы " + rows.length);
        }

        int vectorSize = vector.getSize();

        if (vectorSize > getColumnsCount()) {
            throw new IllegalArgumentException("Размер введенного вектора больше, чем число столбцов матрицы." +
                    " Размер вектора = " + vectorSize + ", число столбцов матрицы " + getColumnsCount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumnByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0. Индекс = " + index);
        }

        if (index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Размер матрицы меньше, чем указанный индекс. Индекс = " + index);
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            resultVector.setComponent(i, getRowByIndex(i).getComponent(index));
        }

        return resultVector;
    }

    public void transpose() {
        Vector[] resultVectors = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            resultVectors[i] = new Vector(getColumnByIndex(i));
        }

        rows = resultVectors;
    }

    public void multiplyByScalar(double number) {
        for (Vector row : rows) {
            row.multiplyByScalar(number);
        }
    }

    public double getDeterminant() {
        int rowsCount = rows.length;
        int columnsCount = getColumnsCount();

        if (rowsCount != columnsCount) {
            throw new ArithmeticException("Нельзя вычислять определитель у прямоугольной матрицы. " +
                    "Размеры матрицы: " + rowsCount + "x" + columnsCount);
        }

        if (columnsCount == 1) {
            return rows[0].getComponent(0);
        }

        if (columnsCount == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < rowsCount; i++) {
            double[][] algebraicComplement = new double[rowsCount - 1][columnsCount - 1];

            for (int j = 1; j < rowsCount; j++) {
                int complementColumnsCount = 0;

                for (int k = 0; k < columnsCount; k++) {
                    if (k == i) {
                        continue;
                    }

                    algebraicComplement[j - 1][complementColumnsCount] = rows[j].getComponent(k);
                    complementColumnsCount++;
                }
            }

            determinant += rows[0].getComponent(i) * (new Matrix(algebraicComplement)).getDeterminant() * Math.pow(-1, i);
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < rows.length; i++) {
            stringBuilder.append(rows[i]);

            if (i != rows.length - 1)
                stringBuilder.append(", ");
        }

        return stringBuilder.append("}").toString();
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Длина вектора должна равняться числу столбцов в матрице!." +
                    " Длина вектора " + vector.getSize() + ", число столбцов " + getColumnsCount());
        }

        double[] resultRows = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            resultRows[i] = Vector.getScalarProduct(rows[i], vector);
        }

        return new Vector(resultRows);
    }

    public void add(Matrix matrix) {
        checkSizeEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkSizeEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkSizeEquality(matrix1, matrix2);

        Matrix matrix = new Matrix(matrix1);
        matrix.add(matrix2);

        return matrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkSizeEquality(matrix1, matrix2);

        Matrix matrix = new Matrix(matrix1);
        matrix.subtract(matrix2);

        return matrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        int matrix1ColumnsCount = matrix1.getColumnsCount();

        if (matrix1ColumnsCount != matrix2.rows.length) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!" +
                    " Количество столбцов первой матрицы " + matrix1ColumnsCount + ", количество строк второй матрицы " + matrix2.rows.length);
        }

        int matrix2ColumnsCount = matrix2.getColumnsCount();

        double[][] resultElements = new double[matrix1.rows.length][matrix2ColumnsCount];

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2ColumnsCount; j++) {
                for (int k = 0; k < matrix1ColumnsCount; k++) {
                    resultElements[i][j] += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }
            }
        }

        return new Matrix(resultElements);
    }

    private static void checkSizeEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount() || matrix1.rows.length != matrix2.rows.length) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать! Размеры матриц: " +
                    matrix1.rows.length + "x" + matrix1.getColumnsCount() + " и " + matrix2.rows.length + "x" + matrix2.getRowsCount());
        }
    }
}
