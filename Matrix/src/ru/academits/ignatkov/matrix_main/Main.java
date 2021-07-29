package ru.academits.ignatkov.matrix_main;

import ru.academits.ignatkov.matrix.Matrix;
import ru.academits.ignatkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
//        Matrix matrix = new Matrix(new Vector[]{
//                new Vector(new double[]{0, 12, -12, 12, 6}),
//                new Vector(new double[]{3, -9, 9, 9, -6}),
//                new Vector(new double[]{0, 0, -2, 4, -2}),
//                new Vector(new double[]{-3, -17, 13, 3, -8}),
//                new Vector(new double[]{0, 0, 4, -8, 0}),
//        });

        Matrix matrix = new Matrix(new Vector[]{
                new Vector(new double[]{0, 12, -12}),
                new Vector(new double[]{3, -9, 9}),
                new Vector(new double[]{0, 0, -2}),
        });

        System.out.println(matrix);
//        matrix.multiplyByScalar(2);
//        System.out.println(matrix);

        System.out.println(matrix.getDeterminant());

    }
}
