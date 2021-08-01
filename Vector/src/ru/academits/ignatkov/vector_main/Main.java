package ru.academits.ignatkov.vector_main;

import ru.academits.ignatkov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] vectorComponents = {1, 2};
        Vector vector1 = new Vector(3, vectorComponents);
        Vector vector2 = new Vector(4, new double[]{3, 7, 3, 4});

        vector1.add(vector2);
        System.out.println("Вектор после сложения: " + vector1);

        vector1.subtract(vector2);
        System.out.println("Вектор после вычитания: " + vector1);

        vector1.multiplyByScalar(7);
        System.out.println("Вектор после умножения на скаляр: " + vector1);

        vector1.reverse();
        System.out.println("Вектор после разворота: " + vector1);

        System.out.println("Длина вектора: " + vector1.getLength());

        int index = 3;
        System.out.printf("Компонента вектора %s с индексом %d равна %f%n", vector1, index, vector1.getComponent(index));

        vector1.setComponent(index, 12);
        System.out.printf("Теперь компонента вектора %s с индексом %d равна %f%n", vector1, index, vector1.getComponent(index));

        Vector vector3 = new Vector(new double[]{3, -2, -5});
        Vector vector4 = new Vector(vector2);

        System.out.printf("Вектор %s %s вектору %s, но %s вектору %s%n", vector2,
                vector2.equals(vector3) ? "равен" : "не равен", vector3,
                vector2.equals(vector4) ? "равен" : "не равен", vector4);

        System.out.printf("По хэш-коду векторы %s и %s %s%n", vector2, vector4,
                vector2.hashCode() == vector4.hashCode() ? "равны" : "не равны");

        System.out.printf("Сумма векторов %s и %s = %s%n", vector3, vector4, Vector.getSum(vector3, vector4));
        System.out.printf("Разность векторов %s и %s = %s%n", vector3, vector4, Vector.getDifference(vector3, vector4));
        System.out.printf("Скалярное произведение векторов %s и %s = %s%n", vector1, vector2, Vector.getScalarProduct(vector1, vector2));
    }
}
