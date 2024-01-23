package com.backjoon.solution.problem_6497;

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

        int N, M;
        boolean isDone = false;

        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(input.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                System.out.print(sb);
                return;
            }

            List<Node> roads = new ArrayList<>();
            int sum = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(input.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                int weight = Integer.parseInt(st.nextToken());
                sum += weight;

                roads.add(new Node(from, to, weight));
            }

            roads.sort((o1, o2) -> o1.weight - o2.weight);

            UnionFind unionFind = new UnionFind(N);

            int minSum = 0;

            for (Node road : roads) {
                if (!unionFind.union(road.from, road.to)) {
                    minSum += road.weight;
                }
            }

            int saving = sum - minSum;
            sb.append(saving).append('\n');
        }
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