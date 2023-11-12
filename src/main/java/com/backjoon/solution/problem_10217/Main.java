package com.backjoon.solution.problem_10217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, M;
    static List<Node>[] graph;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(input.readLine());

        for (int j = 1; j <= K; j++) {
            st = new StringTokenizer(input.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new List[N + 1];
            for (int i = 1; i <= N; i++)
                graph[i] = new ArrayList<>();

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(input.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                graph[from].add(new Node(to, cost, time));
            }

            dist = new int[N + 1][L + 1];
            for (int i = 1; i <= N; i++) {
                Collections.sort(graph[i]);
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            int res = findMinDist();

            System.out.println(res == -1 ? "Poor KCM" : res);
        }
    }

    public static int findMinDist() {
        // 다익트라를 활용하여, 각 정점으로 부터 거리 기록

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));

        dist[1][0] = 0; // 해당 정점, 비용 = 최소시간 DP

        while (!pq.isEmpty()) {
            Node vertex = pq.poll();

            if (vertex.to == N) {
                return vertex.time;
            }

            if (dist[vertex.to][vertex.cost] < vertex.time) continue;

            for (Node nextV : graph[vertex.to]) {
                int time = vertex.time + nextV.time;
                int cost = vertex.cost + nextV.cost;

                if (cost > L || dist[nextV.to][cost] <= time) continue;

                for (int t = cost + 1; t <= L; t++) {
                    if (dist[nextV.to][t] <= time) break;
                    dist[nextV.to][t] = time;
                }

                dist[nextV.to][cost] = time;
                pq.add(new Node(nextV.to, cost, time));
            }
        }
        return -1;
    }
}

class Node implements Comparable<Node>{
    int to, cost, time;

    public Node(int to, int cost, int time) {
        this.to = to;
        this.cost = cost;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) { // 시간을 기준으로 정렬
        return this.time - o.time;
    }
}