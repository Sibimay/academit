package ru.academits.ignatkov.vector_main;

import ru.academits.ignatkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] a = {1, 2};
        Vector vector1 = new Vector(3, a);
        Vector vector2 = new Vector(4, new double[]{3, 7, 3, 4});

        vector1.addVector(vector2);
        System.out.println("После сложения векторов получился вектор " + vector1);

        // Вернем первый вектор к изначальному состоянию
        vector1 = new Vector(3, a);

        vector1.subtractVector(vector2);
        System.out.println("После вычитания векторов получится вектор " + vector1);
    }
}
