package com.backjoon.solution.problem_1766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] degree;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        degree = new int[N+1];
        graph = new ArrayList[N+1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            degree[to]++;
        }

        topologicalPrioritySorting();
    }

    public static void topologicalPrioritySorting() {
        Queue<Integer> minQue = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0)
                minQue.offer(i);
        }

        while(!minQue.isEmpty()) {
            int v = minQue.poll();

            sb.append(v).append(' ');

            for (int nV : graph[v]) {
                // if (degree[nV] == 0) continue;

                degree[nV]--;
                if (degree[nV] == 0)
                    minQue.offer(nV);
            }
        }

        System.out.println(sb);
    }
}