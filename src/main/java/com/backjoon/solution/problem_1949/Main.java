package com.backjoon.solution.problem_1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] citizen;
    static boolean[] isVisit;
    static int[][] dp; // [우수마을 or 일반마을][마을 번호]
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        st = new StringTokenizer(input.readLine());

        citizen = new int[N+1];
        graph = new List[N+1];
        for (int i = 1; i <= N; i++) {
            citizen[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }


        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }

        // dfs로 탐색을 하면서, dp에 데이터를 저장 -> 다시 이 데이터로 dp 갱신
        isVisit = new boolean[N+1];
        dp = new int[2][N+1];
        for (int i = 1; i <= N; i++)
            dp[0][i] = citizen[i];

        dfs(1);
        System.out.println(Math.max(dp[0][1], dp[1][1]));
    }

    public static void dfs(int vIdx) {
        // 인근 마을 탐색, isVisit == false
        // 이후에, dp갱신

        isVisit[vIdx] = true;

        for (int nextVIdx : graph[vIdx]) {
            if (isVisit[nextVIdx]) continue;

            dfs(nextVIdx);

            dp[1][vIdx] += Math.max(dp[0][nextVIdx], dp[1][nextVIdx]);
            dp[0][vIdx] += dp[1][nextVIdx];
        }
    }
}