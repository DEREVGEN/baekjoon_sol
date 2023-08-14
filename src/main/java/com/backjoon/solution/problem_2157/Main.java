package com.backjoon.solution.problem_2157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static List<Cities> cities = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        inputData();
        int res = findMaxRoute();

        System.out.println(res);
    }

    public static void inputData() throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= K; i++) {
            int a, b, c;

            st = new StringTokenizer(input.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a >= b)
                continue;

            cities.add(new Cities(a, b, c));
        }
    }

    public static int findMaxRoute() {

        int[][] dp = new int[M+1][N+1];

        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).start == 1)
                dp[2][cities.get(i).dest] = Math.max(dp[2][cities.get(i).dest], cities.get(i).cost);
        }

        for (int i = 2; i <= M; i++) {
            for (int j = 1; j < cities.size(); j++) {
                if (dp[i-1][cities.get(j).start] == 0)
                    continue;

                dp[i][cities.get(j).dest] = Math.max(dp[i][cities.get(j).dest], dp[i-1][cities.get(j).start] + cities.get(j).cost);
            }
        }

        int maxRes = 0;
        for (int i = 2; i <= M; i++) {
            maxRes = Math.max(maxRes, dp[i][N]);
        }

        return maxRes;
    }
}


class Cities{
    int start, dest, cost;

    public Cities(int start, int dest, int cost) {
        this.start = start;
        this.dest = dest;
        this.cost = cost;
    }
}
