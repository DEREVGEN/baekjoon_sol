package com.backjoon.solution.problem_2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dist;
    static int N;
    final static int MAX = 100000000;
    static int[][] memo;
    static int fullVisited;

    static public void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(input.readLine());

        dist = new int[N][N];
        memo = new int[N][1 << N];
        fullVisited = (1 << N) - 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = tsp(0, 1);

        System.out.println(res);

    }

    public static int tsp(int i, int mask) {

        // only when mask remain ith bit and 0th bit(start node) set,
        if (mask == fullVisited) {
            if (dist[i][0] == 0)
                return MAX;
            return dist[i][0];
        }

        // visited
        if (memo[i][mask] != 0)
            return memo[i][mask];

        memo[i][mask] = MAX;

        for (int j = 0; j < N; j++) {
            if ((mask & (1 << j)) == 0 && dist[i][j] != 0) {
                memo[i][mask] = Math.min(memo[i][mask], tsp(j, mask | (1 << j)) + dist[i][j]);
            }
        }

        return memo[i][mask];
    }
}
