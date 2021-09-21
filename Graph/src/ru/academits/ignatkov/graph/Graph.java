package ru.academits.ignatkov.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] matrix;

    public Graph(int[][] matrix) {
        int rowsCount = matrix.length;
        int columnsCount = matrix[0].length;

        if (rowsCount != columnsCount) {
            throw new UnsupportedOperationException("Матрица не квадратная. " +
                    "Размеры матрицы: " + rowsCount + "x" + columnsCount);
        }
        
        this.matrix = matrix;
    }

    public int getSize() {
        return matrix.length;
    }

    public void traverseWidth(IntConsumer consumer) {
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

    public void traverseDepthRecursion(IntConsumer consumer) {
        traverseDepthRecursion(0, new boolean[matrix.length], consumer);
    }

    private void traverseDepthRecursion(int currentNodeIndex, boolean[] visited, IntConsumer consumer) {
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

    public void traverseDepth(IntConsumer consumer) {
        boolean[] visited = new boolean[matrix.length];

        Deque<Integer> stack = new LinkedList<>();

        for (int nodeIndex = 0; nodeIndex < matrix.length; nodeIndex++) {
            if (visited[nodeIndex]) {
                continue;
            }

            stack.addLast(nodeIndex);

            while (!stack.isEmpty()) {
                int poppedIndex = stack.removeLast();

                if (visited[poppedIndex]) {
                    continue;
                }

                consumer.accept(poppedIndex);

                visited[poppedIndex] = true;

                for (int j = matrix.length - 1; j >= 0; j--) {
                    if (j != poppedIndex && matrix[poppedIndex][j] > 0) {
                        stack.addLast(j);
                    }
                }
            }
        }
    }
}
