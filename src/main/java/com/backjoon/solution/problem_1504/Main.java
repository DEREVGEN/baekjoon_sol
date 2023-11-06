package com.backjoon.solution.problem_1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Node>[] graph;
    static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        int v1, v2;

        st = new StringTokenizer(input.readLine());

        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int startTov1Dist = dijkstra(1, v1);
        int startTov2Dist = dijkstra(1, v2);
        if (startTov1Dist == MAX && startTov2Dist == MAX) {
            System.out.println(-1);
            return;
        }
        int v1v2Dist = dijkstra(v1, v2); // v1=v2, v2=v1
        if (v1v2Dist == MAX) {
            System.out.println(-1);
            return;
        }
        int destTov1Dist = dijkstra(v1, N);
        int destTov2Dist = dijkstra(v2, N);
        if (destTov1Dist == MAX && destTov2Dist == MAX) {
            System.out.println(-1);
            return;
        }

        int res1 = startTov1Dist + v1v2Dist + destTov2Dist;
        int res2 = startTov2Dist + v1v2Dist + destTov1Dist;

        System.out.println(Math.min(res1, res2));
    }

    public static int dijkstra(int startIdx, int destIdx) {
        Queue<Node> pq = new PriorityQueue<>();
        boolean[] isVisit = new boolean[N+1];

        pq.add(new Node(startIdx, 0));

        while (!pq.isEmpty()) {
            Node vertex = pq.poll();

            if (isVisit[vertex.to]) continue;

            if (vertex.to == destIdx) {
                return vertex.cost;
            }

            isVisit[vertex.to] = true;

            for (Node nextVertex : graph[vertex.to]) {
                pq.add(new Node(nextVertex.to, vertex.cost + nextVertex.cost));
            }
        }

        return MAX;
    }
}

class Node implements Comparable<Node>{
    int to, cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}