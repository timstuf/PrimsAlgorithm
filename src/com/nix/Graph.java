package com.nix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    private int[][] matrix;

    Graph(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix[i].length);
    }

    public Edge[] Prim() {
        Edge[] ostov = new Edge[matrix.length - 1];

        boolean[] usedVertex = new boolean[matrix.length];
        Arrays.fill(usedVertex, false);
        usedVertex[0] = true;

        int[][] newMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) System.arraycopy(matrix, 0, newMatrix, 0, matrix.length);

        for (int i = 0; i < matrix.length - 1; i++) {
            Edge edge = findCheapestEdge(usedVertex, newMatrix);
            usedVertex[edge.secondVertex - 1] = true;
            newMatrix[edge.secondVertex - 1][edge.firstVertex - 1] = 0;
            newMatrix[edge.firstVertex - 1][edge.secondVertex - 1] = 0;
            ostov[i] = edge;
        }
        return ostov;
    }

    private Edge findCheapestEdge(boolean[] usedVertex, int[][] matrix) {
        int min = Integer.MAX_VALUE;
        int firstVertex = 0;
        int secondVertex = 0;
        for (int i = 0; i < usedVertex.length; i++) {
            if (usedVertex[i] == false) continue;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) continue;
                if (usedVertex[j] == true) continue;
                if (min > matrix[i][j]) {
                    min = matrix[i][j];
                    firstVertex = i + 1;
                    secondVertex = j + 1;
                }
            }
        }

        return new Edge(firstVertex, secondVertex);
    }

    static class Edge {
        public int firstVertex;
        public int secondVertex;

        Edge(int first, int second) {
            firstVertex = first;
            secondVertex = second;
        }
    }


}
