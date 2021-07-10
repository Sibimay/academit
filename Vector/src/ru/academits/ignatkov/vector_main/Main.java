package ru.academits.ignatkov.vector_main;

import ru.academits.ignatkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] a = new double[] {1,2};
        Vector vector = new Vector(3, a);
        System.out.println(vector);
    }
}
