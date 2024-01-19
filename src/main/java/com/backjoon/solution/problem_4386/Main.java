package com.backjoon.solution.problem_4386;

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

        int N = Integer.parseInt(input.readLine());

        List<Coord> coords = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());

            coords.add(new Coord(a, b));
        }


        List<Node> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double weight = dist(coords.get(i), coords.get(j));

                points.add(new Node(i, j, weight));
            }
        }

        points.sort((o1, o2) -> Double.compare(o1.weight, o2.weight));

        UnionFind unionFind = new UnionFind(points.size());

        double sum = 0;
        for (int i = 0; i < points.size(); i++) {
            Node point = points.get(i);

            if (!unionFind.union(point.from, point.to))
                sum += point.weight;
        }

        System.out.println(sum);

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