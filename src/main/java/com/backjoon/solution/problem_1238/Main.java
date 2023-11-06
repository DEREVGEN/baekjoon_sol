package com.backjoon.solution.problem_1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, T;
    static List<Node>[] graph;
    static List<Node>[] rGraph;
    static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        rGraph = new List[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            rGraph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            rGraph[to].add(new Node(from, cost));
        }


        int[] partyDist = goToHome(graph);
        int[] homeDist = goToHome(rGraph);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, partyDist[i] + homeDist[i]);
        }
        System.out.println(max);
    }

    public static int[] goToHome(List<Node>[] g) {
        Queue<Node> pq = new PriorityQueue<>();
        int[] visitNum = new int[N+1];

        Arrays.fill(visitNum, MAX);

        pq.add(new Node(T, 0));

        while (!pq.isEmpty()) {
            Node vertex = pq.poll();

            if (visitNum[vertex.to] != MAX) continue;
            visitNum[vertex.to] = vertex.cost;

            for (Node nextVertex : g[vertex.to]) {
                pq.add(new Node(nextVertex.to, vertex.cost + nextVertex.cost));
            }
        }

        return visitNum;
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