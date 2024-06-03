package com.backjoon.solution.problem_11657;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<Node> edges;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Node(from, to, weight));
        }

        dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        if (!bellmanFord(0)) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < N; i++) {
                sb.append(dist[i] == Long.MAX_VALUE ? -1 : dist[i]).append('\n');
            }

            System.out.println(sb);
        }
    }

    public static boolean bellmanFord(int start) {
        dist[start] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Node edge = edges.get(j);

                if (dist[edge.from] != Long.MAX_VALUE && dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;

                    if (i == N - 1)
                        return false;
                }
            }
        }

        return true;
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