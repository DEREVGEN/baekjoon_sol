package com.backjoon.solution.problem_1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Node> edges;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Node(from, to, weight));
        }
        edges.sort((Node o1, Node o2) -> o1.weight - o2.weight);

        System.out.println(findMST());
    }

    public static long findMST() {

        int[] parent = new int[N+1];

        for (int i = 1; i <= N; i++)
            parent[i] = i;

        long sum = 0;
        int a = 0;

        for (var iter = edges.iterator(); iter.hasNext();) {
            Node edge = iter.next();

            if (includeEdge(parent, edge.from, edge.to)) {
                a = edge.weight;
                sum += a;
            }
        }

        return sum - a;
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
    int from, to, weight;

    public Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}