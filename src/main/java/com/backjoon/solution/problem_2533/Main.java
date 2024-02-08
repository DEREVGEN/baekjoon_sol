package com.backjoon.solution.problem_2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Integer>[] graph;
    static int[][] dp; // [checked or unchecked][정점]
    static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        graph = new List[N+1];
        for (int i = 1; i < N+1; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
        isVisit = new boolean[N+1];
        dp = new int[2][N+1];

        for (int i = 1; i < N+1; i++)
            dp[0][i] = 1;
        dfs(1);

        System.out.println(Math.min(dp[0][1], dp[1][1]));
    }

    public static void dfs(int idx) {
        isVisit[idx] = true;

        for (int childIdx : graph[idx]) {
            if (isVisit[childIdx]) continue;

            dfs(childIdx);

            // 현재노드(=[idx])가 비어있을 때,
            dp[1][idx] += dp[0][childIdx];
            // 현재노드(=[idx])가 채워져 있을때,
            dp[0][idx] += Math.min(dp[0][childIdx], dp[1][childIdx]);
        }
    }
}