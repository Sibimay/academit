package ru.academits.ignatkov.graph_main;

import ru.academits.ignatkov.graph.Graph;

import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 1},
                {0, 1, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 0}
        };
        Graph graph = new Graph(matrix);
        IntConsumer print = node -> System.out.println("Visited node: " + node);

        System.out.println("traverseWidth");
        graph.traverseWidth(print);
        System.out.println();

        System.out.println("traverseDepthRecursion");
        graph.traverseDepthRecursion(print);
        System.out.println();

        System.out.println("traverseDepth");
        graph.traverseDepth(print);
    }
}