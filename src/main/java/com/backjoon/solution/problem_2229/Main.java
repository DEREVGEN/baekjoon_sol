package com.backjoon.solution.problem_2229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        st = new StringTokenizer(input.readLine());

        int[] nums = new int[N+1];

        for (int i = 1; i <= N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++) {
            int max = nums[i];
            int min = nums[i];

            for (int j = i; j > 0; j--) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);

                dp[i] = Math.max(dp[i], max - min + dp[j-1]);
            }
        }

        System.out.println(dp[N]);

    }
}