package ru.academits.ignatkov.matrix_main;

import ru.academits.ignatkov.matrix.Matrix;
import ru.academits.ignatkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(new Vector[]{
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{7, 5})});

        System.out.println(matrix);
    }
}
