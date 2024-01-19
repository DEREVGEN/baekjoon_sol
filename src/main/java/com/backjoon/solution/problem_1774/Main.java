package com.backjoon.solution.problem_1774;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Coord> points = new ArrayList<>();

        int N, M;
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points.add(new Coord(x, y));
        }

        List<Node> gates = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double weight = dist(points.get(i), points.get(j));

                gates.add(new Node(i, j, weight));
            }
        }
        gates.sort((o1, o2) -> Double.compare(o1.weight, o2.weight));

        UnionFind unionFind = new UnionFind(points.size());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            unionFind.union(a, b);
        }


        double sum = 0;
        for (int i = 0; i < gates.size(); i++) {
            Node gate = gates.get(i);

            if (!unionFind.union(gate.from, gate.to)) {
                sum += gate.weight;
            }
        }

        System.out.printf("%.2f", sum);

    }

    public static double dist(Coord a, Coord b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}

class UnionFind {
    int N;
    int[] parent;

    public UnionFind(int n) {
        N = n;
        parent = new int[N+1];
        // 각 노드 번호로 초기화

        //  index 시작점 0 또는 1
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }


    public int findParent(int nodeNum) {
        if (parent[nodeNum] != nodeNum)
            parent[nodeNum] = findParent(parent[nodeNum]);

        return parent[nodeNum];
    }


    public boolean union(int v1, int v2) {
        // path compression 코드
        int pV1 = findParent(v1);
        int pV2 = findParent(v2);

        boolean isCycle = false;

        if (pV1 == pV2) {
            isCycle = true;
            return isCycle;
        }

        if (pV1 < pV2)
            parent[pV2] = pV1;
        else
            parent[pV1] = pV2;

        return isCycle;
    }
}

class Node {
    int from, to;
    double weight;

    public Node(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class Coord {
    double x, y;

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }
}