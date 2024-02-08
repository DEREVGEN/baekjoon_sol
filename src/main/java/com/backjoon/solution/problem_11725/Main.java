package com.backjoon.solution.problem_11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] graph;
    static int N;
    static int[] parent;
    static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        graph = new List[N+1];
        parent = new int[N+1];
        isVisit = new boolean[N+1];
        for (int i = 1; i < N+1; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(input.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        findDFS(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N+1; i++) {
            sb.append(parent[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void findDFS(int idx) {
        isVisit[idx] = true;
        for (int childIdx : graph[idx]) {
            if (isVisit[childIdx]) continue;
            parent[childIdx] = idx;
            findDFS(childIdx);
        }
    }
}