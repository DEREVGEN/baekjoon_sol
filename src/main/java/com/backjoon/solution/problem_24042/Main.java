package com.backjoon.solution.problem_24042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, i));
            graph[to].add(new Node(from, i));
        }

        long res = findMinTimeToGoal();
        System.out.println(res);
    }

    public static long findMinTimeToGoal() {

        Queue<Node> pq = new PriorityQueue<>(((o1, o2) -> Long.compare(o1.time, o2.time)));

        long[] visitNum = new long[N+1];
        Arrays.fill(visitNum, Long.MAX_VALUE);

        pq.add(new Node(1, 0));
        visitNum[1] = 0;

        while (!pq.isEmpty()) {
            Node vertex = pq.poll();

            for (var nextVertex : graph[vertex.to]) {
                long time = vertex.time + ((nextVertex.time - vertex.time) % M + M) % M + 1;

                if (visitNum[nextVertex.to] > time) {
                    pq.add(new Node(nextVertex.to, time));
                    visitNum[nextVertex.to] = time;
                }
            }
        }

        return visitNum[N];
    }
}

class Node {
    int to;
    long time;

    public Node(int to, long time) {
        this.to = to;
        this.time = time;
    }
}