package com.backjoon.solution.problem_1865;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(input.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Node> edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(input.readLine());

                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());

                edges.add(new Node(from, to, weight));
                edges.add(new Node(to, from, weight));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(input.readLine());

                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());

                edges.add(new Node(from, to, -weight));
            }

            if (bellmanFord(N, M, W, edges)) {
                sb.append("NO").append('\n');
            } else {
                sb.append("YES").append('\n');
            }
        }

        System.out.println(sb);

    }

    public static boolean bellmanFord(int N, int M, int W, List<Node> edges) {
        long[] dist = new long[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[0] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < edges.size(); j++) {
                Node edge = edges.get(j);

                if (dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }


        for (int j = 0; j < edges.size(); j++) {
            Node edge = edges.get(j);

            if (dist[edge.from] + edge.weight < dist[edge.to]) {
                return false;
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