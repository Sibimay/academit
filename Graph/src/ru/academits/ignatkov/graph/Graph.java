package ru.academits.ignatkov.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    private final int[][] matrix;

    public Graph(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getSize() {
        return matrix.length;
    }

    public void traverseWidth(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[matrix.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.remove();
            consumer.accept(currentNode);

            for (int i = 1; i < matrix[currentNode].length; i++) {
                if (matrix[currentNode][i] != 0 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }

            if (queue.isEmpty()) {
                for (int i = 1; i < visited.length; i++) {
                    if (!visited[i]) {
                        queue.add(i);
                        visited[i] = true;
                    }
                }
            }
        }
    }

    public void traverseDepthRecursion(int currentNodeIndex, boolean[] visited, Consumer<Integer> consumer) {
        visited[currentNodeIndex] = true;
        consumer.accept(currentNodeIndex);

        for (int i = 1; i < matrix[currentNodeIndex].length; i++) {
            if (matrix[currentNodeIndex][i] == 1 && !visited[i]) {
                traverseDepthRecursion(i, visited, consumer);
            }
        }

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                traverseDepthRecursion(i, visited, consumer);
            }
        }
    }

    public void traverseDepth(Consumer<Integer> consumer) {
        boolean[] visited = new boolean[matrix.length];

        Deque<Integer> stack = new LinkedList<>();
        stack.add(0);
        visited[0] = true;

        while (!stack.isEmpty()) {
            int currentNode = stack.remove();
            consumer.accept(currentNode);

            for (int i = matrix[currentNode].length - 1; i >= 1; i--) {
                if (matrix[currentNode][i] != 0 && !visited[i]) {
                    stack.add(i);
                    visited[i] = true;
                }
            }

            if (stack.isEmpty()) {
                for (int i = 1; i < visited.length; i++) {
                    if (!visited[i]) {
                        stack.add(i);
                        visited[i] = true;
                    }
                }
            }
        }
    }
}
