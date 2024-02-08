package com.backjoon.solution.problem_15681;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, R, Q;
    static List<Integer>[] graph;
    static boolean[] isVisit;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        isVisit = new boolean[N+1];
        dp = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(input.readLine());
            sb.append(dp[q]).append('\n');
        }
        System.out.println(sb);

    }

    public static void dfs(int idx) {
        isVisit[idx] = true;
        for (int childIdx : graph[idx]) {
            if (isVisit[childIdx]) continue;

            isVisit[childIdx] = true;
            dfs(childIdx);
            dp[idx] += dp[childIdx];
        }
        dp[idx]++;
    }

}