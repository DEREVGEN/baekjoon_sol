package com.backjoon.solution.problem_2213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int[] vertex;
    static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> paths = new ArrayList<>();
    static int[][] dp;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        vertex = new int[N+1];
        dp = new int[2][N+1];
        visit = new boolean[N+1];

        st = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++)
            vertex[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++)
            edges.add(new ArrayList<>());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 양방향
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        exploreTree(0, 1);
        tracePath(0, 1);
        System.out.println(Math.max(dp[0][1], dp[1][1]));
        Collections.sort(paths);
        paths.forEach(path -> System.out.print(path + " "));
    }

    public static void exploreTree(int parentIdx, int currentIdx) {

        // 방문x
        dp[0][currentIdx] = 0;
        // 방문o
        dp[1][currentIdx] = vertex[currentIdx];

        for (var iter = edges.get(currentIdx).iterator(); iter.hasNext(); ) {
            int nextIdx = iter.next();

            if (nextIdx == parentIdx) continue;

            exploreTree(currentIdx, nextIdx);
            // 방문 안할 시, 다음 노드로부터 방문o or 방문x
            dp[0][currentIdx] += Math.max(dp[0][nextIdx], dp[1][nextIdx]);
            // 방문 했으면, 다음 노드는 방문x
            dp[1][currentIdx] += dp[0][nextIdx];
        }
    }

    public static void tracePath(int parentIdx, int currentIdx) {

        if (dp[0][currentIdx] < dp[1][currentIdx] && !visit[parentIdx]) {
            paths.add(currentIdx);
            visit[currentIdx] = true;
        }

        for (var iter = edges.get(currentIdx).iterator(); iter.hasNext(); ) {
            int nextIdx = iter.next();

            if (nextIdx == parentIdx) continue;

            tracePath(currentIdx, nextIdx);
        }
    }
}