package ru.academits.ignatkov.graph_main;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0}
        };

        System.out.println("WidthRound");
        widthRound(graph);
        System.out.println();
        System.out.println("depthRoundRecursion");
        depthRoundRecursion(graph, 0, new boolean[graph.length]);
        System.out.println();
        System.out.println("depthRound");
        depthRound(graph);
    }

    public static void widthRound(int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(graph[0]);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] currentNode = queue.remove();
            System.out.println(Arrays.toString(currentNode));

            for (int i = 1; i < graph[0].length; i++) {
                if (currentNode[i] != 0 && !visited[i]) {
                    queue.add(graph[i]);
                    visited[i] = true;
                }
            }

            if (queue.isEmpty()) {
                for (int i = 1; i < visited.length; i++) {
                    if (!visited[i]) {
                        queue.add(graph[i]);
                        visited[i] = true;
                    }
                }
            }
        }
    }

    public static void depthRoundRecursion(int[][] graph, int currentNodeIndex, boolean[] visited) {
        visited[currentNodeIndex] = true;

        System.out.println(Arrays.toString(graph[currentNodeIndex]));

        for (int i = 1; i < graph[currentNodeIndex].length; i++) {
            if (graph[currentNodeIndex][i] == 1 && !visited[i]) {
                depthRoundRecursion(graph, i, visited);
            }
        }

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                depthRoundRecursion(graph, i, visited);
            }
        }
    }

    public static void depthRound(int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        Deque<int[]> stack = new LinkedList<>();
        stack.add(graph[0]);
        visited[0] = true;

        while (!stack.isEmpty()) {
            int[] currentNode = stack.remove();
            System.out.println(Arrays.toString(currentNode));

            for (int i = graph[0].length - 1; i >= 1; i--) {
                if (currentNode[i] != 0 && !visited[i]) {
                    stack.add(graph[i]);
                    visited[i] = true;
                }
            }

            if (stack.isEmpty()) {
                for (int i = 1; i < visited.length; i++) {
                    if (!visited[i]) {
                        stack.add(graph[i]);
                        visited[i] = true;
                    }
                }
            }
        }
    }
}