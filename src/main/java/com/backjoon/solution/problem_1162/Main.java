package com.backjoon.solution.problem_1162;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int  N, M, K;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int j = 1; j <= M; j++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph[from].add(new Node(to, cost, 0));
            graph[to].add(new Node(from, cost, 0));
        }

        findMinDistToRoad();
    }

    public static void findMinDistToRoad() {

        Queue<Node> pq = new PriorityQueue<>(((o1, o2) -> Long.compare(o1.cost, o2.cost)));

        long[][] visitNum = new long[N+1][K+1];
        for (int i = 1; i <= N; i++)
            Arrays.fill(visitNum[i], Long.MAX_VALUE);

        pq.add(new Node(1, 0, 0));
        visitNum[1][0] = 0;

        while (!pq.isEmpty()) {
            Node vertex = pq.poll();

            if (visitNum[vertex.to][vertex.count] < vertex.cost) continue;

            for (Node nextV : graph[vertex.to]) {
                if (vertex.count < K && visitNum[nextV.to][vertex.count+1] > vertex.cost) {
                    visitNum[nextV.to][vertex.count+1] = vertex.cost;
                    pq.add(new Node(nextV.to, vertex.cost, vertex.count+1));
                }

                long dist = vertex.cost + nextV.cost;
                if (visitNum[nextV.to][vertex.count] > dist) {
                    visitNum[nextV.to][vertex.count] = dist;
                    pq.add(new Node(nextV.to, dist, vertex.count));
                }
            }
        }


        long minDist = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++)
            minDist = Long.min(minDist, visitNum[N][i]);

        System.out.println(minDist);
    }
}

class Node {
    int to, count;
    long cost;

    public Node(int to, long cost, int count) {
        this.to = to;
        this.cost = cost;
        this.count = count;
    }
}