package com.backjoon.solution.problem_16398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Node> graph;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        int[][] inputMap = new int[N][N];
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                 inputMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                graph.add(new Node(i, j, inputMap[i][j]));
            }
        }

        graph.sort((o1, o2) -> o1.cost - o2.cost);

        long cost = 0;

        for (Node v : graph) {
            if (union(v.from, v.to)) {
                cost += v.cost;
            }
        }

        System.out.println(cost);
    }


    public static int findParent(int v) {
        if (parent[v] != v) {
            parent[v] = findParent(parent[v]);
        }

        return parent[v];
    }

    public static boolean union(int v1, int v2) {
        int pV1 = findParent(v1);
        int pV2 = findParent(v2);

        if (pV1 == pV2)
            return false;

        if(pV1 < pV2)
            parent[pV2] = pV1;
        else
            parent[pV1] = pV2;

        return true;
    }

}

class Node {
    int from, to, cost;

    public Node(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}