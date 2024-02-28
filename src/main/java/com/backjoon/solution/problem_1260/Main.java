package com.backjoon.solution.problem_1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static int N, M, V;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        // [1, N]
        graph = new List[N+1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        StringBuilder sb = new StringBuilder();
        boolean[] isVisit = new boolean[N+1];

        sb.append(V).append(' ');
        isVisit[V] = true;

        dfs(V, sb, isVisit);
        System.out.println(sb);

        sb = new StringBuilder();
        isVisit = new boolean[N+1];
        bfs(V, sb, isVisit);
        System.out.println(sb);
    }

    public static void dfs(int start, StringBuilder sb, boolean[] isVisit) {
        for (int node : graph[start]) {
            if (isVisit[node]) continue;
            isVisit[node] = true;
            sb.append(node).append(' ');

            dfs(node, sb, isVisit);
        }
    }

    public static void bfs(int start, StringBuilder sb, boolean[] isVisit) {
        Queue<Integer> queue = new LinkedList<>();

        isVisit[start] = true;
        queue.add(start);
        sb.append(start).append(' ');

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int nextNode : graph[node]) {
                if (isVisit[nextNode]) continue;
                isVisit[nextNode] = true;
                queue.add(nextNode);
                sb.append(nextNode).append(' ');
            }
        }

    }
}