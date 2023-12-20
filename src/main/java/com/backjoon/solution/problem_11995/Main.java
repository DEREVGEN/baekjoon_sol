package com.backjoon.solution.problem_11995;

import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer> vertical;
    static List<Integer> horizontal;

    static int A, B;
    static int N, M;

    static List<Node> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        vertical = new ArrayList<>();
        horizontal = new ArrayList<>();

        st = new StringTokenizer(input.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        vertical.add(0);
        horizontal.add(0);
        for (int i = 0; i < N; i++)
            vertical.add(Integer.parseInt(input.readLine()));
        for (int i = 0; i < M; i++)
            horizontal.add(Integer.parseInt(input.readLine()));
        vertical.add(A);
        horizontal.add(B);

        Collections.sort(vertical);
        Collections.sort(horizontal);

        int n = vertical.size();
        int m = horizontal.size();

        graph = new ArrayList<>();

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {

                int current = i * (n-1) + j;
                int down = (i+1) * (n-1) + j;
                int right = current + 1;

                if (i != m-2) {
                    //graph.add(new Node(current, down, horizontal.get(i + 1) - horizontal.get(i)));
                    graph.add(new Node(current, down, vertical.get(j + 1) - vertical.get(j)));

                }

                if (j != n-2) {
                    //graph.add(new Node(current, right, vertical.get(j + 1) - vertical.get(j)));
                    graph.add(new Node(current, right, horizontal.get(i + 1) - horizontal.get(i)));
                }
            }
        }
        Collections.sort(graph, (o1, o2) -> {
            return Long.compare(o1.weight, o2.weight);
        });

        long res = findMST((n-1) * (m-1));
        System.out.println(res);
    }

    public static long findMST(int n) {
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        long sum = 0;

        for (Node edge: graph) {
            if (includeEdge(parent, edge.from, edge.to)) {
                sum += edge.weight;
            }
        }

        return sum;
    }

    public static int findParent(int[] parent, int nodeNum) {

        if (parent[nodeNum] != nodeNum)
            parent[nodeNum] = findParent(parent, parent[nodeNum]);

        return parent[nodeNum];
    }

    public static boolean includeEdge(int[] parent, int v1, int v2) {

        int pV1 = findParent(parent, v1);
        int pV2 = findParent(parent, v2);

        if (pV1 == pV2)
            return false;

        if (pV1 < pV2)
            parent[pV2] = pV1;
        else
            parent[pV1] = pV2;

        return true;
    }
}

class Node {
    int from, to;
    long weight;

    public Node(int from, int to, long weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}