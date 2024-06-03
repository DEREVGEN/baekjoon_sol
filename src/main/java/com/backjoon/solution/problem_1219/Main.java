package com.backjoon.solution.problem_1219;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node> edges = new ArrayList<>();
        List<Integer>[] graph = new List[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Node(from, to, weight));
            graph[from].add(to);
        }

        st = new StringTokenizer(input.readLine());
        int[] cost = new int[N];
        for (int i = 0; i < N; i++)
            cost[i] = Integer.parseInt(st.nextToken());


        long[] dist = new long[N];

        bellmanFord(N, start, end, edges, graph, dist, cost);

    }

    public static void bellmanFord(int N, int start, int end, List<Node> edges, List<Integer>[] graph, long[] dist, int[] cost) {
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[start] = -cost[start];

        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < edges.size(); j++) {
                Node edge = edges.get(j);

                if (dist[edge.from] != Long.MAX_VALUE && dist[edge.from] + edge.weight - cost[edge.to] < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight - cost[edge.to];
                }
            }
        }

        if (dist[end] == Long.MAX_VALUE) {
            System.out.println("gg");
            return;
        }

        for (int j = 0; j < edges.size(); j++) {
            Node edge = edges.get(j);

            if (dist[edge.from] != Long.MAX_VALUE && dist[edge.from] + edge.weight - cost[edge.to] < dist[edge.to]) {
                if (bfs(edge.from, end, N, graph)) {
                    System.out.println("Gee");
                    return;
                }
            }
        }


        System.out.println(-dist[end]);
    }

    public static boolean bfs(int s, int e, int N, List<Integer>[] graph) {
        boolean[] isVisited = new boolean[N];

        isVisited[s] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        while (!queue.isEmpty()) {
            int c = queue.poll();

            if (c == e) {
                return true;
            }

            for (int nv : graph[c]) {
                if (isVisited[nv]) continue;
                isVisited[nv] = true;
                queue.offer(nv);
            }
        }

        return false;
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