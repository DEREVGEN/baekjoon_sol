package com.backjoon.solution.problem_17835;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static List<Node>[] graph;
    static final long MAX = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N+1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[to].add(new Node(from, cost));
        }

        int[] interviewV = new int[K];
        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < K; i++)
            interviewV[i] = Integer.parseInt(st.nextToken());

        long[] minDist = findInterviewMinDist(interviewV);

        long maxDist = 0;
        int maxIdx = 0;
        for (int i = 1; i <= N; i++) {
            if (maxDist < minDist[i]) {
                maxDist = minDist[i];
                maxIdx = i;
            }
        }
        System.out.println(maxIdx);
        System.out.println(maxDist);
    }

    public static long[] findInterviewMinDist(int[] interviewV) {
        Queue<Node> pq = new PriorityQueue<>();

        long[] visitNum = new long[N+1];
        Arrays.fill(visitNum, MAX);

        for (int i = 0; i < K; i++) {
            pq.add(new Node(interviewV[i], 0));
            visitNum[interviewV[i]] = 0;
        }

        while (!pq.isEmpty()) {
            Node vertex = pq.poll();

            if (visitNum[vertex.to] < vertex.cost) continue;

            for (Node nextV : graph[vertex.to]) {
                if (visitNum[nextV.to] > vertex.cost + nextV.cost) {
                    pq.add(new Node(nextV.to, vertex.cost + nextV.cost));
                    visitNum[nextV.to] = vertex.cost + nextV.cost;
                }
            }

        }

        return visitNum;
    }
}

class Node implements Comparable<Node>{
    int to;
    long cost;

    public Node(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.cost, o.cost);
    }
}