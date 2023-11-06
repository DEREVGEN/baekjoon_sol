package com.backjoon.solution.problem_1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Node>[] graph;
    static int startIdx, destIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        M = Integer.parseInt(input.readLine());

        graph = new List[N+1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int j = 1; j <= M; j++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(input.readLine());

        startIdx = Integer.parseInt(st.nextToken());
        destIdx = Integer.parseInt(st.nextToken());

        int res = dijkstra(startIdx, destIdx);
        System.out.println(res);
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

        return -1;
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