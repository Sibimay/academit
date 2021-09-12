package ru.academits.ignatkov.graph_main;

import ru.academits.ignatkov.graph.Graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0}
        };
        Graph graph = new Graph(matrix);
        Consumer<Integer> print = node -> System.out.println("Visited node: " + node);

        System.out.println("WidthRound");
        graph.traverseWidth(print);
        System.out.println();

        System.out.println("depthRoundRecursion");
        graph.traverseDepthRecursion(0, new boolean[graph.getSize()], print);
        System.out.println();

        System.out.println("depthRound");
        graph.traverseDepth(print);
    }
}