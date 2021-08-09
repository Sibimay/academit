package ru.academits.ignatkov.matrix_main;

import ru.academits.ignatkov.matrix.Matrix;
import ru.academits.ignatkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] elements1 = {2, -5, 4};
        Vector vector1 = new Vector(elements1);

        double[] elements2 = {-3, 7, -8};
        Vector vector2 = new Vector(elements2);

        double[] elements3 = {3, -4, 3};
        Vector vector3 = new Vector(elements3);

        Vector[] vectors = {vector1, vector2, vector3};

        Matrix matrix1 = new Matrix(vectors);
        System.out.println(matrix1.toString());

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println("Копируем матрицу matrix1 в матрицу matrix2: " + matrix2);
        System.out.println("Получаем размер матрицы matrix2: " + matrix2.getRowsCount());

        System.out.println("Вектор по индексу 1: " + matrix2.getRowByIndex(1));

        System.out.println("Число столбцов матрицы matrix2 равно: " + matrix2.getColumnsCount());

        Vector vector4 = new Vector(elements1);
        matrix2.setRowByIndex(2, vector4);

        System.out.println("matrix2 до траснпонирования: " + matrix2);
        matrix2.transpose();
        System.out.println("matrix2 после траснпонирования: " + matrix2);

        vector3 = matrix2.getColumnByIndex(1);
        System.out.println("Вектора-столбец по индексу 1: " + vector3);

        matrix2.multiplyByScalar(3);
        System.out.println("Умножение матрицы на 3: " + matrix2);

        System.out.println("Определитель матрицы matrix2 равен: " + matrix2.getDeterminant());

        System.out.println("Умножение матрицы на вектор: " + matrix2.multiplyByVector(vector2));

        Matrix matrix3 = new Matrix(matrix2);
        matrix3.add(matrix1);
        System.out.println("Сумма матриц: " + matrix3);

        Matrix matrix4 = new Matrix(matrix2);
        matrix3.subtract(matrix1);
        System.out.println("Разность матриц: " + matrix4);

        System.out.println("Сумма матриц (статический метод): " + Matrix.getSum(matrix2, matrix1));

        System.out.println("Разность матриц (статический метод): " + Matrix.getDifference(matrix2, matrix1));

        System.out.println("Произведение матриц:" + Matrix.getProduct(matrix2, matrix1));
    }
}
