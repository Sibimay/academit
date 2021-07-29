package ru.academits.ignatkov.matrix_main;

import ru.academits.ignatkov.matrix.Matrix;
import ru.academits.ignatkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(new Vector[]{
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{4, 5, 6}),
                new Vector(new double[]{7, 8, 9})});

        System.out.println(matrix);
//        matrix.multiplyByScalar(2);
//        System.out.println(matrix);

        matrix.getDeterminant();

    }
}
