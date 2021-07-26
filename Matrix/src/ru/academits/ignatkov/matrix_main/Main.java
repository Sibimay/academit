package ru.academits.ignatkov.matrix_main;

import ru.academits.ignatkov.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(new double[][] {{1,2,3}, {1,2}});

        Matrix matrix1 = new Matrix(matrix);

        System.out.println(matrix);
    }
}
