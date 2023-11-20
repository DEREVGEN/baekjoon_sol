package com.backjoon.solution.problem_13907;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int start, goal;
    static List<Node>[] graph;
    static int[] tax;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(input.readLine());
        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 양방향
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        tax = new int[K+1];
        for (int i = 1; i <= K; i++) {
            tax[i] = tax[i-1] + Integer.parseInt(input.readLine());
        }

        int[][] pathCost = findMinPathWithTax();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= K; i++) {
            // 세금이 i번째 일 때, 간선이 0개부터 M개 까지 검사.
            int min = Integer.MAX_VALUE;

            for (int j = 0; j <= N; j++) {
                // 간선개수에 대하여..

                if (pathCost[j][goal] == Integer.MAX_VALUE) continue;
                min = Math.min(min, pathCost[j][goal] + tax[i] * j);
            }
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }

    public static int[][] findMinPathWithTax() {

        int[][] visitNum = new int[N+1][N+1]; // [지나온 도시 개수], [목적지]
        for (int i = 0; i <= N; i++)
            Arrays.fill(visitNum[i], Integer.MAX_VALUE);

        Queue<DNode> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        pq.add(new DNode(start, 0, 0));
        visitNum[start][0] = 0;

        while(!pq.isEmpty()) {
            DNode vertex = pq.poll();

            if (visitNum[vertex.edgeCnt][vertex.to] < vertex.cost) continue;

            for (Node nextV : graph[vertex.to]) {
                int dist = vertex.cost + nextV.cost;

                if (vertex.edgeCnt < N && visitNum[vertex.edgeCnt + 1][nextV.to] > dist) {
                    visitNum[vertex.edgeCnt + 1][nextV.to] = dist;
                    pq.add(new DNode(nextV.to, dist, vertex.edgeCnt + 1));
                }
            }
        }

        return visitNum;
    }
}

class Node {
    int to, cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

class DNode {
    int to, cost, edgeCnt;

    public DNode(int to, int cost, int edgeCnt) {
        this.to = to;
        this.cost = cost;
        this.edgeCnt = edgeCnt;
    }
}