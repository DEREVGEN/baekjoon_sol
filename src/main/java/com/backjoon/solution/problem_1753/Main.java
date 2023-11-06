package com.backjoon.solution.problem_1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int startIdx;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        startIdx = Integer.parseInt(input.readLine());

        graph = new ArrayList[N+1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        int[] resDist = find();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < resDist.length; i++) {
            sb
              .append(resDist[i] == -1 ? "INF" : resDist[i])
              .append("\n");
        }
        System.out.println(sb);
    }

    public static int[] find() {
        Queue<Node> pq = new PriorityQueue<>();

        int[] visitNum = new int[N+1];
        Arrays.fill(visitNum, -1);

        pq.add(new Node(startIdx, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visitNum[node.to] != -1) continue;
            visitNum[node.to] = node.cost;

            for (Node nextNode : graph[node.to]) {
                pq.add(new Node(nextNode.to, node.cost + nextNode.cost));
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