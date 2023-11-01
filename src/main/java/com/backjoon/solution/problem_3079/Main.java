package com.backjoon.solution.problem_3079;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] cost = new int[N];
        long max = 0;
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(input.readLine());
            max = Math.max(max, cost[i]);
        }

        long startCost = 0;
        long endCost = max * M;
        long minCost = Long.MAX_VALUE;

        while (startCost <= endCost) {
            long sum = 0;
            long midCost = (startCost + endCost) / 2;

            for (int i = 0; i < N; i++) {
                sum += midCost / cost[i];
                if (sum >= M)
                    break;
            }

            if (sum >= M) {
                endCost = midCost - 1;
                minCost = Math.min(minCost, midCost);
            } else {
                startCost = midCost + 1;
            }
        }
        System.out.println(minCost);
    }
}